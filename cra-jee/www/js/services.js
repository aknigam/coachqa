angular.module('starter.services', [])

    .service('LoginService', function($q, $http, $state, ApiEndpoint) {
        return {
            loginUser: function(name, pw) {
                var deferred = $q.defer();
                var promise = deferred.promise;

              $http.post(ApiEndpoint.url + '/authenticate?username=' + name + '&password=' + pw).then(function(result) {
                deferred.resolve(result);
              }, function(error) {
                deferred.reject('Wrong credentials.');
                alert('Authentication error : ' + error.data);
              });
              promise.success = function(fn) {
                promise.then(fn);
                return promise;
              }
              promise.error = function(fn) {
                promise.then(null, fn);
                return promise;
              }
             return promise;
            },
            logout : function() {
              $http({
                      method: 'DELETE',
                      url: ApiEndpoint.url + '/authenticate'
                    }).then(function successCallback(response) {
                $state.go('login');
              }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
              });
            }
        }
    })
.factory('Chats', function($http, $q, ApiEndpoint) {
  // Might use a resource here that returns a JSON array

  // Some fake testing data
  var chats = [{
    id: 0,
    name: 'Ben Sparrow',
    lastText: 'You on your way?',
    face: 'https://pbs.twimg.com/profile_images/514549811765211136/9SgAuHeY.png'
  }, {
    id: 1,
    name: 'Max Lynx',
    lastText: 'Hey, it\'s me',
    face: 'https://avatars3.githubusercontent.com/u/11214?v=3&s=460'
  }, {
    id: 2,
    name: 'Adam Bradleyson',
    lastText: 'I should buy a boat',
    face: 'https://pbs.twimg.com/profile_images/479090794058379264/84TKj_qa.jpeg'
  }, {
    id: 3,
    name: 'Perry Governor',
    lastText: 'Look at my mukluks!',
    face: 'https://pbs.twimg.com/profile_images/598205061232103424/3j5HUXMY.png'
  }, {
    id: 4,
    name: 'Mike Harrington',
    lastText: 'This is wicked good ice cream.',
    face: 'https://pbs.twimg.com/profile_images/578237281384841216/R3ae1n61.png'
  }];
  var hostName, portNumber, apiUrl;

  return {
    all: function() {
      return chats;
    },
    remove: function(chat) {
      chats.splice(chats.indexOf(chat), 1);
    },
    get: function(chatId) {
      for (var i = 0; i < chats.length; i++) {
        if (chats[i].id === parseInt(chatId)) {
          return chats[i];
        }
      }
      return null;
    },
    postQuestion : function(data) {
      var deferred = $q.defer();
          // http://localhost:9090/api/questions/ask/submit
          $http.post(ApiEndpoint.url + '/api/questions/ask/submit', data,{
            contentType: "application/json",
            accept: "application/json",
            timeout: 15000
          })
            .then(function(result) {
                    deferred.resolve(result);
                  }, function(error) {
                              alert('Error while posting question, due to ' + error);
                  });
          return deferred.promise;
    },
    answerQuestion : function(data) {
      var deferred = $q.defer();
      // http://localhost:9090/api/questions/answer/submit
      $http.post(ApiEndpoint.url + '/api/questions/answer/submit', data,{
        contentType: "application/json",
        accept: "application/json",
        timeout: 15000
      }).then(function(result){
        deferred.resolve(result);
      }, function(error) {
        alert('Error while posting answer, due to ' + error);
      });
      return deferred.promise;
    },
    getConfig : function() {
      return $http.get('../connection.properties');
    }
  };
});
