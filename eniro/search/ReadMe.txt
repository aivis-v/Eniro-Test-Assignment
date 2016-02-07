INSTRUCTIONS

1) Building the project
 
1.1)In Terminal / command prompt window
 - open the project folder containing the pom.xml file
 - run "maven build" without the "

1.2) In Eclipse
 - Creat new Maven Run Configuration
 - Base directory: ${workspace_loc:/search}
 - goal: build
 
 You should see a new "target" directory appear under the search project containing a file named "search-0.0.1-SNAPSHOT.jar"
 
 2) Running the server
 2.1) Open a terminal / command prompt window
 2.2) Navigate to the "search\target" folder
 2.3) run the following command:
 
      java -jar search-0.0.1-SNAPSHOT.jar server
   
The server should start up within seconds and display a noticable warning about missing health checks. 

You can see the web version at http://localhost:8080/enirotest



USAGE NOTES

Search phrases and filters are separated with a ,

Search results should show up below the search button within seconds.

JSON details:
You can test the endpoint localhost:8080/search manually by sending the following JSON to it in a POST request:

JSON example: 

{
	"phrases": ["pizza", "kebab"],
	"filters": ["companyInfo", "address"]
}

Phrases and filters are separated with a ,

* there is no check for filter names being only lower case or upper case. 
* Please type the filter name exactly the same way it is returned in the Eniro API search results


Suggested software for manual testing:

I'm Only Resting - http://www.swensensoftware.com/im-only-resting

Url: http://localhost:8080/search
Method: POST
Headers: 

Accept: application/json, 
Content-Type: application/json 

Body: 

{
	"phrases": ["pizza", "kebab"],
	"filters": ["companyInfo", "address"]
}
