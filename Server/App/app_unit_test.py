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

        print("COMPLETED test_get_yelp_businesses")


    def test_get_yelp_biz_info(self):
        true = True
        false = False
        testResponse = {
            "body": {
                "is_claimed": true,
                "rating": 4.5,
                "is_closed": false,
                "review_count": 4652,
                "name": "Gary Danko",
                "photos": [
                    "https://s3-media3.fl.yelpcdn.com/bphoto/MWMKouFOmJk3DX6pIuWUwQ/o.jpg",
                    "https://s3-media3.fl.yelpcdn.com/bphoto/8A5W_BZbMldUaf1v6HKfSQ/o.jpg",
                    "https://s3-media2.fl.yelpcdn.com/bphoto/agP5JnxcXlXV-RGszrMZPQ/o.jpg"
                ],
                "url": "https://www.yelp.com/biz/gary-danko-san-francisco?adjust_creative=3Dx7aHnhBtvSYve-Z1rojw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_lookup&utm_source=3Dx7aHnhBtvSYve-Z1rojw",
                "price": "$$$$",
                "coordinates": {
                    "latitude": 37.80587,
                    "longitude": -122.42058
                },
                "hours": [
                    {
                        "hours_type": "REGULAR",
                        "open": [
                            {
                                "start": "1730",
                                "end": "2200",
                                "day": 0,
                                "is_overnight": false
                            },
                            {
                                "start": "1730",
                                "end": "2200",
                                "day": 1,
                                "is_overnight": false
                            },
                            {
                                "start": "1730",
                                "end": "2200",
                                "day": 2,
                                "is_overnight": false
                            },
                            {
                                "start": "1730",
                                "end": "2200",
                                "day": 3,
                                "is_overnight": false
                            },
                            {
                                "start": "1730",
                                "end": "2200",
                                "day": 4,
                                "is_overnight": false
                            },
                            {
                                "start": "1730",
                                "end": "2200",
                                "day": 5,
                                "is_overnight": false
                            },
                            {
                                "start": "1730",
                                "end": "2200",
                                "day": 6,
                                "is_overnight": false
                            }
                        ],
                        "is_open_now": false
                    }
                ],
                "phone": "+14157492060",
                "image_url": "https://s3-media3.fl.yelpcdn.com/bphoto/MWMKouFOmJk3DX6pIuWUwQ/o.jpg",
                "location": {
                    "city": "San Francisco",
                    "country": "US",
                    "address2": "",
                    "address3": "",
                    "state": "CA",
                    "address1": "800 N Point St",
                    "zip_code": "94109"
                },
                "id": "gary-danko-san-francisco",
                "categories": [
                    {
                        "alias": "newamerican",
                        "title": "American (New)"
                    },
                    {
                        "alias": "french",
                        "title": "French"
                    }
                ]
            },
            "headers": {
                "Content-Type": "application/json"
            },
            "statusCode": 200
        }        
        getYelpBizInfoURL = "https://zg5hawbfuh.execute-api.us-west-2.amazonaws.com/prod/getYelpBizInfo"
        params = {"id": "gary-danko-san-francisco"}
        response = requests.get(getYelpBizInfoURL, params=params)

        # It's easier to compare individual entries in the dictionary
        responseDict = json.loads(response.text)
        responseClaim = responseDict["body"]["is_claimed"]
        testClaim = testResponse["body"]["is_claimed"]
        self.assertEqual(responseClaim, testClaim, msg="responseClaim != testClaim")

        responseRating = responseDict["body"]["rating"]
        testRating = testResponse["body"]["rating"]
        self.assertEqual(responseRating, responseRating, msg="responseRating != responseRating")

        responseName = responseDict["body"]["name"]
        testName = testResponse["body"]["name"]
        self.assertEqual(responseName, testName, msg="responseName != testName")

        responseURL = responseDict["body"]["url"]
        testURL = testResponse["body"]["url"]
        self.assertEqual(responseURL, testURL, msg="responseURL != testURL")

        responsePrice = responseDict["body"]["price"]
        testPrice = testResponse["body"]["price"]
        self.assertEqual(responsePrice, testPrice, msg="responsePrice != testPrice")

        responseLatitude = responseDict["body"]["coordinates"]["latitude"]
        testLatiitude = testResponse["body"]["coordinates"]["latitude"]
        self.assertEqual(responseLatitude, testLatiitude, msg="responseLatitude != testLatiitude")

        responseLongitude = responseDict["body"]["coordinates"]["longitude"]
        testLongitude = testResponse["body"]["coordinates"]["longitude"]
        self.assertEqual(responseLongitude, testLongitude, msg="responseLongitude != testLongitude")

        for index in range(0,6):
            responseHours = responseDict["body"]["hours"][0]["open"][index]
            testHours = testResponse["body"]["hours"][0]["open"][index]
            self.assertEqual(responseHours, testHours, msg="responseHours != testHours")

        responsePhone = responseDict["body"]["phone"]
        testPhone = testResponse["body"]["phone"]
        self.assertEqual(responsePhone, testPhone, msg="responsePhone != testPhone")

        responseImageURL = responseDict["body"]["image_url"]
        testImageURL = testResponse["body"]["image_url"]
        self.assertEqual(responseImageURL, testImageURL, msg="responseImageURL != testImageURL")

        responseLocation = responseDict["body"]["location"]
        testLocation = testResponse["body"]["location"]
        self.assertEqual(responseLocation, testLocation, msg="responseLocation != testLocation")

        responseID = responseDict["body"]["id"]
        testID = testResponse["body"]["id"]
        self.assertEqual(responseID, testID, msg="responseID != testID")

        for index in range(0,1):
            responseCategory = responseDict["body"]["categories"][index]
            testCategory = testResponse["body"]["categories"][index]
            self.assertEqual(responseCategory, testCategory, msg="responseCategory != testCategory")

        print("COMPLETED test_get_yelp_biz_info")


def main():
    testApp = TestApp()

if __name__ == '__main__':
    unittest.main()
