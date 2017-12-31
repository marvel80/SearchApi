## Search using AWS services

This project demonstrates building a `Spring Boot` based API for a simple search functionality. The search is powered by AWS ElasticSearch. We explore deploying the API on ElasticBeanstalk & finally, place the API behind AWS API Gateway.

A call to API is thus fronted by gateway which is protected by AWS IAM user. All the calls to API will need proper authorization to be processed. 

Please see the section on API call which explains more on how calls are actually made.


## API calls

#### Elastic Search API call : 
A sample call to search indexed documents , calling the ES directly on index `pc-plan-data`,  vis ES API's. Note how we have constructed a `bool query` in the body of the request.

```

curl -X POST \
  https://search-pc-search-2stnwuev5f62c5omwl6e7oprum.us-west-1.es.amazonaws.com/pc-plan-data/_search \
  -H 'Content-Type: application/json' \
  -d '{
"from" : 0, 
"size" : 2,
  "query" : {
    "bool" : {
      "must" : {
        "match" : {
          "SPONS_DFE_MAIL_US_STATE" : {
            "query" : "TX"
          }
        }
      }
    }
  }
}
'
```

#### Call to API, which internally calls elastic search: 
Call to our API using query parameters that constructs the appropriate ES query based on parameters.
```
curl -X GET \
  'http://pc-search-env.us-west-1.elasticbeanstalk.com/plan/?sponsor=MAVERICK&state=NY' \
  -H 'Accept: application/json' \
  -H 'Content-Type: application/json'
  ```
 
 #### API call via AWS gateway:
 The same call as above, but now the URL is of API Gateway & thereby the calls need to have `Authorization` header. 
 ```
 curl -X GET \
  'https://s9ujgpl5cl.execute-api.us-west-1.amazonaws.com/dev/plans/?state=CA' \
  -H 'Authorization: AWS4-HMAC-SHA256 Credential=AKIAJ4PQJE5NU5B7B6LQ/20171231/us-west-1/execute-api/aws4_request, SignedHeaders=content-type;host;x-amz-date, Signature=d69335f5029795d8431a2201243d43ec250319d5b2ca78230d04b2274b32ce56' \
  -H 'Content-Type: application/json' \
  -H 'X-Amz-Date: 20171231T073119Z'
 ```
 
 Call without this header will result  : in `403` errors, with folllwing messsage : 

 ```
 {
    "message": "Missing Authentication Token"
}
```
 
## Prerequisites
- AWS account   
- JDK    
- Maven    


 ## Running the tests
Run the Junit tests via IDE or CLI.


## Deployment
TODO


## Components

`pom.xml` declares Spring boot starter as the parent pom which gets us most of required dependencies. 

```
<parent>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-parent</artifactId>
  <version>1.5.9.RELEASE</version>
  <relativePath/> 
</parent>

```

#### Libraries used :   
`spring-boot-starter-web`   - to get support of all spring web related annotations and functionalities.   
`spring-boot-starter-test`   - to get all dependencies for writing unit tests.   
`spring-boot-starter-actuator`   - to get boot actuator that exposes helpful endpoints.       
`spring-boot-starter-data-elasticsearch`   - to get elastic search JAVA libs for writing ES DSL.  

`jackson-databind`  - to bind JSON responses to our API.   
`lombok`   - helper lib for generating less code in POJO's.   
`opencsv`   - open source lib for parsing CSV files (our data is to be indexed is in CSV format).   
`commons-httpclient`   - some helper http libs.   


## Built With

* [Spring FrameWork](https://spring.io) 
* [Maven](https://maven.apache.org/) - For Dependency Management



## Authors

* **Ankur Bhat** - *Initial work*


## Acknowledgments

AWS documentation 
Elastic Search Java SDK documentation
