define(['./module'], function (services) {
    'use strict';

    services.service('RestService', ['$http', '$sce', 'LoggerService', function restUtil($http, $sce, LoggerService) {

            var config = {
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
                }
            };

            var configJson = {
                headers: {
                    'Content-Type': 'application/json; charset=UTF-8'
                }
            };

            var configFile = {
                headers: {
                    enctype: 'multipart/form-data'
                }
            };

            var configExport = {
                responseType: 'arraybuffer'
            };

            var logSuccess = function (type, data) {
                LoggerService.debug(' -> SUCCESS with:');
                LoggerService.debug(data);
                LoggerService.debug('END ' + type);
            };

            var logError = function (type, data) {
                LoggerService.debug(' -> ERROR with:');
                LoggerService.debug(data);
                LoggerService.debug('END ' + type);
            };

            var CustomError = function (status) {
                throw {
                    name: 'CustomError',
                    message: 'Falha ao conectar no servidor: ' + status
                };
            };

            return {
                post: function (url, params) {
                    LoggerService.debug('POST on: ' + url);
                    var dados = $http
                            .post(url, params, configJson)
                            .then(function (data, status, headers, config) {
                                logSuccess('POST', data);
                                return data;
                            }, function (data, status, headers, config) {
                        logError('POST', data.message);
                        throw { 'objeto' : data, 'status' : status };
                    });
                    return dados;
                },
                put: function (url, params) {
                    LoggerService.debug('PUT on: ' + url);
                    var dados = $http
                        .put(url, params, this.config)
                        .then(function (data, status, headers, config) {
                            logSuccess('PUT', data);
                            return data;
                        }).catch(function (data, status, headers, config) {
                            logError('PUT', data.message);
                            return data.message;
                        });
                    return dados;
                },
                delete: function (url, params) {
                    LoggerService.debug('DELETE on: ' + url);
                    var dados = $http
                        .delete(url, params, this.config)
                        .then(function (data, status, headers, config) {
                            logSuccess('DELETE', data);
                            return data;
                        }).catch(function (data, status, headers, config) {
                            logError('DELETE', data.message);
                            return data.message;
                        });
                    return dados;
                },
                postJson: function (url, params) {
                    LoggerService.debug('POST on: ' + url);
                    var dados = $http
                            .post(url, params, this.configJson)
                            .then(function (data, status, headers, config) {
                                logSuccess('POST', data);
                                return data;
                            }).catch(function (data, status, headers, config) {
                        logError('POST', data.message);
                        return data.message;
                    });
                    return dados;
                },
                postFile: function (url, file) {
                    var dados = $http
                            .post(url, file, this.configFile)
                            .then(function (data, status, headers, config) {
                                return data;
                            }).catch(function (data, status, headers, config) {
                        if (status !== 500)
                            return data.message;
                        else
                            throw CustomError(status);
                    });
                    return dados;
                },
                get: function (url) {
                    LoggerService.debug('BEGIN GET on: ' + url);
                    var dados = $http
                            .get(url, this.config)
                            .then(function (data, status, headers, config) {
                                logSuccess('GET', data);
                                return data;
                            }).catch(function (data, status, headers, config) {
                        logError('GET', data);
                        if (status !== 500)
                            return data.message;
                        else
                            throw CustomError(status);
                    });
                    return dados;
                },
                exportDoc: function (url, data) {
                    return $http
                            .post(url, data, this.configExport)
                            .then(
                                    function (data, status, headers, config) {
                                        var blob = new Blob([data], {type: 'application/pdf'});
                                        var objectUrl = URL.createObjectURL(blob);
                                        window.open(objectUrl, "_blank");
                                    }).catch(
                            function (data, status) {
                                console.log('Request failed with status: ' + status);
                                return data;
                            }
                    );
                },
                download: function (url, data) {
                    return $http
                            .post(url, data, this.configExport)
                            .then(
                                    function (data, status, headers, config) {
                                        return data;
                                    }).catch(
                            function (data, status) {
                                console.log('Request failed with status: ' + status);
                                return data;
                            }
                    );
                },
                test: function (str) {
                    return str;
                }
            };
        }]);

});