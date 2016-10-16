from __future__ import print_function

import requests
import json

def get_yelp_token(event, context):
    yelp_data = None
    with open('yelp.json') as data_file:
        yelp_data = json.load(data_file)
    request_data = {
        "grant_type" : "client_credentials",
        "client_id" : yelp_data["client_id"],
        "client_secret" : yelp_data["client_secret"]
    }
    res = requests.post("https://api.yelp.com/oauth2/token", data=request_data)
    return {
        'statusCode': res.status_code,
        'body': res.json(),
        'headers': {
            'Content-Type': 'application/json'
        }
    }
