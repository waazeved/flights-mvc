define(['app', 'core/locale/pt-br', 'core/locale/en-us'], function (app) {
    app.config(function ($translateProvider) {
        $translateProvider.translations('pt-br', ptBr);
        $translateProvider.translations('en-us', enUs);
        $translateProvider.preferredLanguage('pt-br');
        $translateProvider.useSanitizeValueStrategy('escaped');
    })
});
