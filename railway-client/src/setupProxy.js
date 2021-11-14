const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
    app.use(
        ['/railway-system/*'],
        createProxyMiddleware({
            target: 'http://localhost:8080',
            //target: 'https://c19dashboard.lgcc.gov.lk',
            //target: 'https://admin.czportal.lgcc.gov.lk',
            //target: 'http://staging.c19dashboard.lgcc.gov.lk/',
            changeOrigin: true,
        })
    );
};
