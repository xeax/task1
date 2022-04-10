# Task solution

Code structured according to https://docs.spring.io/spring-boot/docs/2.0.0.RELEASE/reference/html/using-boot-structuring-your-code.html

Docker image published on DockerHub: https://hub.docker.com/r/xeax/task1/tags

REST API with Swagger-UI published on Heroku: https://mysterious-ridge-97198.herokuapp.com/swagger-ui/

Endpoints according to requirements:
* creation of a user account:
  https://mysterious-ridge-97198.herokuapp.com/swagger-ui/#/User%20controller/createUsingPOST_1
* addition, view, modification, and deletion of quotes:
  https://mysterious-ridge-97198.herokuapp.com/swagger-ui/#/Quotes%20controller
* voting on quotes (either upvote or downvote).
  https://mysterious-ridge-97198.herokuapp.com/swagger-ui/#/Voting%20controller/voteUsingPOST
* view of the top 10 quotes, the details of each quote
  https://mysterious-ridge-97198.herokuapp.com/swagger-ui/#/Quotes%20search%20controller

I didn't have time to implement effective data extraction for graphs and rendering. Here is just an endpoint for data extraction for the graph. https://mysterious-ridge-97198.herokuapp.com/swagger-ui/#/Voting%20controller/getVoteStatisticsUsingGET (demo data filled for quoteId=1). Perhaps an interesting option would be to draw a graph on the front with animation, for example, using https://d3js.org/.

Demo data installed:
 - 4 users
 - 3 quotes from users 2-4
 - 4 votes on quote #1 from users 1-4

PS: All actions except user registration are treated as committed by user #1 (login="test"), since authorization is not implemented.