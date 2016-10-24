from __future__ import print_function

import requests
import json

YELP_BUSINESSES_SEARCH='https://api.yelp.com/v3/businesses/search'

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

def strip_business_info(business_json):
    return {
        "rating" : business_json['rating'],
        "price" : business_json['price'],
        "coordinates" : business_json['coordinates'],
        "categories" : business_json['categories'],
        "url" : business_json['url']
    }

#Here we'll deserialize the json, strip out the excess info, and reserialize
#what we want to keep (and avoid showing closed businesses)
#
#Here are the fields we want to keep:
#    url,
#    categories,
#    price,
#    rating,
#    coordinates
def strip_response(json_response):
    return map(strip_business_info, 
               filter(lambda biz_info: not biz_info['is_closed'], 
                      json_response['businesses']))

def send_request(headers, event):
    return requests.get(YELP_BUSINESSES_SEARCH, headers=headers, params=event)

def get_response(request):
    return {
        "statusCode" : request.status_code,
        "body" : strip_response(request.json()),
        "headers" : {
            "Content-Type" : "application/json"
        }
    }

def get_yelp_businesses(event, context):
    token = get_yelp_token()
    headers = {
        "Authorization": token["token_type"] + " " + token["access_token"]
    }
    return get_response(send_request(headers, event))

