const axios = require('axios');
const cheerio = require('cheerio');
const delay = ms => new Promise(resolve => setTimeout(resolve, ms));

const herbArticles = [
    { herb: "Asoka", url: "https://rimbakita.com/bunga-asoka/#:~:text=Caranya%20adalah%20dengan%20merebus%20bunga%20asoka%2C%20bunga%20mawar%2C,maksimal.%20Luka%20memar%20pun%20akan%20sembuh%20lebih%20cepat.", titleSelector: '.entry-title', dateSelector: '.entry-date' },
    { herb: "Bunga Telang", url: "https://www.ibupedia.com/artikel/keluarga/8-manfaat-bunga-telang-dan-cara-mengolahnya-untuk-sajian-sehat-keluarga", titleSelector: 'h1', dateSelector: '.post-date' },
    { herb: "Daun Jambu Biji", url: "https://katadata.co.id/berita/lifestyle/611a1969294d3/7-manfaat-daun-jambu-biji-dan-cara-mengonsumsinya#:~:text=Cuci%205-10%20lembar%20daun%20jambu%20biji%20segar%20dengan,dalam%20saringan%20di%20atas%20cangkir%20atau%20mangkuk%20lebar.", titleSelector: 'h1', dateSelector: '.entry-date' },
    { herb: "Daun Jarak", url: "https://rumahpanduan.com/10-manfaat-daun-jarak-dan-cara-mengolahnya/", titleSelector: 'h1', dateSelector: '.entry-date' },
    { herb: "Daun Jeruk Nipis", url: "https://www.ilmupot.com/manfaat-daun-jeruk-nipis/", titleSelector: 'h1', dateSelector: '.entry-date' },
    { herb: "Daun Pepaya", url: "https://www.daya.id/usaha/artikel-daya/makan-sehat/5-manfaat-daun-pepaya-lengkap-dan-cara-mengolahnya", titleSelector: 'h1', dateSelector: '.entry-date' },
    { herb: "Kayu Putih", url: "https://asgar.or.id/health/makanan-dan-minuman-sehat/khasiat-obat-dan-manfaat-dari-kayu-putih/#:~:text=Cara%20Mengolah%20dan%20Meramu%20Kayu%20Putih%3A%201%201.,genggam%20...%204%204.%20Untuk%20Menyembuhkan%20Luka%20bernanah%3A", titleSelector: 'h1', dateSelector: '.entry-date' },
    { herb: "Lidah Buaya", url: "https://www.detik.com/jabar/jabar-gaskeun/d-6273210/15-manfaat-lidah-buaya-untuk-kesehatan-dan-cara-mengolahnya", titleSelector: 'h1', dateSelector: '.date' },
    { herb: "Semanggi", url: "https://www.alatpertanian.asia/2024/01/daun-semanggi-manfaat-efek-samping-dan.html", titleSelector: 'h1', dateSelector: '.entry-date' },
    { herb: "Daun Sirih", url: "https://artikel.rumah123.com/sederet-manfaat-daun-sirih-untuk-kesehatan-dan-cara-mengolahnya-52745", titleSelector: 'h1', dateSelector: '.entry-date' }
];

const scrapeArticles = async () => {
    let articles = [];
    for (const site of herbArticles) {
        try {
            const { data } = await axios.get(site.url);
            const $ = cheerio.load(data);
            const title = $(site.titleSelector).text().trim();
            const publishDate = $(site.dateSelector).text().trim();
            const url = site.url;
            let description = '';
            let photoUrl = '';

            // Logic to automatically find description and photo URL
            // Modify this logic based on the structure of the HTML
            $('meta').each((index, element) => {
                const property = $(element).attr('property');
                if (property === 'og:description') {
                    description = $(element).attr('content');
                } else if (property === 'og:image') {
                    photoUrl = $(element).attr('content');
                }
            });
            

            if (title && publishDate && url) {
                const article = { title, publishDate, url, herb: site.herb };
                articles.push(article);
            }

            // Delay between requests to avoid rate limiting
            await delay(5000); // 5 seconds delay
        } catch (error) {
            console.error(`Error scraping ${site.herb}:`, error);
        }
    }
    return articles;
};

const getHerbArticles = async (request, h) => {
    try {
        const articles = await scrapeArticles();
        return h.response({
            status: true,
            articles
        }).code(200);
    } catch (error) {
        console.error('Error getting articles:', error);
        return h.response({ message: 'Error getting articles' }).code(500);
    }
};

module.exports = {
    getHerbArticles
};
