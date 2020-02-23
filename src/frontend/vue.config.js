const path = require("path");

module.exports = {
    "transpileDependencies": [
        "vuetify"
    ],
    outputDir: path.resolve(__dirname, "../main/resources/static"),
    devServer: {
        proxy: {
            '/' : {
                target: 'http://localhost:8080',
                ws: true,
                changeOrigin: true
            },
        },
    },
};