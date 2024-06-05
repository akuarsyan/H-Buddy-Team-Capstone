const { getHerbArticles } = require('../controllers/articles');

module.exports = [
    {
        method: 'GET',
        path: '/articles',
        handler: getHerbArticles
    }
];
