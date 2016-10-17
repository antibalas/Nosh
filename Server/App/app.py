from __future__ import print_function

import requests
import json

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

def get_yelp_businesses(event, context):
    token = get_yelp_token()
    headers = {
        "Authorization": token["token_type"] + " " + token["access_token"]
    }

    res = requests.get("https://api.yelp.com/v3/businesses/search", headers=headers, params=event)

    return {
        "statusCode": res.status_code,
        "body" : res.json(),
        "headers" : {
            "Content-Type" : "application/json"
        }
    }