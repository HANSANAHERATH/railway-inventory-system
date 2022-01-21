const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
    app.use(
        ['/railway-system/*'],
        createProxyMiddleware({
            target: 'http://localhost:8080/',
            changeOrigin: true,
        })
    );
};
