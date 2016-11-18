import unittest
import subprocess
import requests
import json

class TestApp(unittest.TestCase):

    def test_get_yelp_businesses(self):
        testResponse = {
            "body": [
                {
                    "rating": 4,
                    "name": "Kelly's French Bakery",
                    "url": "https://www.yelp.com/biz/kellys-french-bakery-santa-cruz?adjust_creative=3Dx7aHnhBtvSYve-Z1rojw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=3Dx7aHnhBtvSYve-Z1rojw",
                    "price": "$$",
                    "coordinates": {
                        "latitude": 36.9589386,
                        "longitude": -122.0484772
                    },
                    "id": "kellys-french-bakery-santa-cruz",
                    "categories": [
                        {
                            "alias": "bakeries",
                            "title": "Bakeries"
                        },
                        {
                            "alias": "coffee",
                            "title": "Coffee & Tea"
                        },
                        {
                            "alias": "desserts",
                            "title": "Desserts"
                        }
                    ]
                }
            ],
            "headers": {
                "Content-Type": "application/json"
            },
            "statusCode": 200
        }

        getFromYelpURL = "https://zg5hawbfuh.execute-api.us-west-2.amazonaws.com/prod/getFromYelp"
        params = {"location": "Santa Cruz CA", "term": "macarons", "limit":1}
        response = requests.get(getFromYelpURL, params=params)

        # It's easier to compare individual entries in the dictionary
        responseDict = json.loads(response.text)
        responseRating = responseDict["body"][0]["rating"]
        testRating = testResponse["body"][0]["rating"]
        self.assertEqual(responseRating, testRating, msg="responseRating != testRating")
        
        responseName = responseDict["body"][0]["name"]
        testName = testResponse["body"][0]["name"]
        self.assertEqual(responseName, testName, msg="responseName != testName")

        responseURL = responseDict["body"][0]["url"]
        testURL = testResponse["body"][0]["url"]
        self.assertEqual(responseURL, testURL, msg="responseURL != testURL")

        responsePrice = responseDict["body"][0]["price"]
        testPrice = testResponse["body"][0]["price"]
        self.assertEqual(responsePrice, testPrice, msg="responsePrice != testPrice")

        responseLatitude = responseDict["body"][0]["coordinates"]["latitude"]
        testLatitude = testResponse["body"][0]["coordinates"]["latitude"]
        self.assertEqual(responseLatitude, testLatitude, msg="responseLatitude != testLatitude")

        responseLongitude = responseDict["body"][0]["coordinates"]["longitude"]
        testLongitude = testResponse["body"][0]["coordinates"]["longitude"]
        self.assertEqual(responseLongitude, testLongitude, msg="responseLongitude != testLongitude")

        responseID = responseDict["body"][0]["id"]
        testID = testResponse["body"][0]["id"]
        self.assertEqual(responseID, testID, msg="responseID != testID")

        for index in range(0,2):
            responseCategory = responseDict["body"][0]["categories"][index]
            testCategory = testResponse["body"][0]["categories"][index]
            self.assertEqual(responseCategory, testCategory, msg="responseCategory != testCategory")


def main():
    testApp = TestApp()
    testApp.test_get_yelp_businesses()

if __name__ == '__main__':
    unittest.main()
