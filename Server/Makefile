# get the root directory
ROOT_DIR := $(shell dirname $(realpath $(lastword $(MAKEFILE_LIST))))

# get the app directory
APP_DIR := $(ROOT_DIR)/App

# get outputs directory
OUTPUT_DIR := $(ROOT_DIR)/Output
OUTPUT_APP_DIR := $(OUTPUT_DIR)/App

# File to be updated with environment variable values (i.e. client id & secret)
OUTPUT_YELP_FILE := $(OUTPUT_APP_DIR)/yelp.json

# get package file 
PACKAGE_FILE := $(OUTPUT_DIR)/package.zip

# copy contents of App directory into Output
copy: create_output_dir
	cp -r $(APP_DIR) $(OUTPUT_DIR)

update_yelp_json:
	sed -i '' -e "s/client_id_dev/${NOSH_YELP_CLIENT_ID}/g" $(OUTPUT_YELP_FILE)
	sed -i '' -e "s/client_secret_dev/${NOSH_YELP_CLIENT_SECRET}/g" $(OUTPUT_YELP_FILE)

zip:
	cd $(OUTPUT_APP_DIR) && zip -r $(PACKAGE_FILE) .

build: create_output_dir copy update_yelp_json zip

create_output_dir:
	mkdir -p $(OUTPUT_DIR)

clean:
	rm -rf $(OUTPUT_DIR)