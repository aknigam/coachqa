angular.module('starter.services', [])

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
          $http.post(ApiEndpoint.url + '/questions/ask/submit', data,{
            contentType: "application/json",
            accept: "application/json",
            timeout: 15000
          })
            .then(function(result) {
                    deferred.resolve(result);
                  }, function(error) {
                              alert('Question posted successfully..... with error' + error);
                  });
          return deferred.promise;
    },
    getConfig : function() {
      return $http.get('../connection.properties');
    }
  };
});
