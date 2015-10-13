Running the cra-jee UI application: 

- First, install Node.js. 
- Set the path to the bin directory of installed Node.js
- Then, install the latest Cordova and Ionic
  
  npm install -g cordova ionic
  
- Move to your cra-jee project
- If the rest server is running other then 9090 port you need to update the proxy mapping
  For updating the proxy mapping find file "gulpfile.js" and update the below section with your rest port
  gulp.task('add-proxy', function() {
	return replace({
                   regex: "http://localhost:{rest-port}",
                   replacement: "http://localhost:8100",
                   paths: replaceFiles,
                   recursive: false,
                   silent: false
                 });
	});

	gulp.task('remove-proxy', function() {
	  return replace({
					   regex: "http://localhost:8100",
					   replacement: "http://localhost:{rest-port}",
					   paths: replaceFiles,
					   recursive: false,
					   silent: false
					 });
	});

	Also update file "ionic.project" as
	
	{
	  "name": "cra-zee",
	  "app_id": "",
	   "proxies": [
		  {
			"path": "/rest-api",
			"proxyUrl": "http://localhost:{rest-port}"
		  }
		]
	}
	
- Now run the command in git bash currently pointing to your cra-jee directive as:
  ionic serve --lab
  
  This will starts the simulation of IOS and Android env in your default browser.