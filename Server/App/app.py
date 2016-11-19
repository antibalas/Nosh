from __future__ import print_function

import requests
import json

YELP_BUSINESSES_SEARCH='https://api.yelp.com/v3/businesses/search'
YELP_BUSINESS_INFO='https://api.yelp.com/v3/businesses/'

def get_yelp_token():
    yelp_data = None
    with open('yelp.json') as data_file:
        yelp_data = json.load(data_file)
    request_data = {
        "grant_type" : "client_credentials",
        "client_id" : yelp_data["client_id"],
        "client_secret" : yelp_data["client_secret"]
    }
    res = requests.post("https://api.yelp.com/oauth2/token", data=request_data)
    return res.json()

#this just makes sure that the key is in there first since yelp will not always
#include all the values we want
def strip_business_info_(business_json, key):
    return business_json[key] if key in business_json else ''

def strip_business_info(business_json):
    return {
        "rating" : strip_business_info_(business_json, 'rating'),
        "price" : strip_business_info_(business_json, 'price'),
        "coordinates" : strip_business_info_(business_json, 'coordinates'),
        "categories" : strip_business_info_(business_json, 'categories'),
        "url" : strip_business_info_(business_json, 'url'),
        "name" : strip_business_info_(business_json, 'name'),
        "id" : strip_business_info_(business_json, 'id'),
        "location" : strip_business_info_(business_json, 'location')
    }

#Here we can filter out businesses we don't want (based on rating, categories,
#etc.)
def filter_businesses(business_json):
    return not business_json['is_closed']

#Here we'll deserialize the json, strip out the excess info, and reserialize
#what we want to keep (and avoid showing closed businesses)
#
#Here are the fields we want to keep:
#    url,
#    categories,
#    price,
#    rating,
#    coordinates
def strip_search_response(json_response):
    return map(strip_business_info, 
               filter(filter_businesses, json_response['businesses']))

def send_request(url, headers, event):
    return requests.get(url, headers=headers, params=event)

def get_response(status_code, body):
    return {
        "statusCode" : status_code,
        "body" : body,
        "headers" : {
            "Content-Type" : "application/json"
        }
    }

#If we send empty parameters to the yelp api, it might return an error
#So we get rid of them here
def get_params(event):
    params = dict()
    for key in event:
        if event[key] != '': params[key] = event[key]
    return params

def get_yelp_businesses(event, context):
    token = get_yelp_token()
    headers = {
        "Authorization": token["token_type"] + " " + token["access_token"]
    }
    request = send_request(YELP_BUSINESSES_SEARCH, headers, get_params(event))
    response = get_response(request.status_code, strip_search_response(request.json()))
    return response

def get_yelp_biz_info(event, context):
    token = get_yelp_token()
    headers = {
        "Authorization": token["token_type"] + " " + token["access_token"]
    }
    id = event["id"]
    url = YELP_BUSINESS_INFO + id
    request = send_request(url, headers, {})
    response = get_response(request.status_code, request.json())
    return response

