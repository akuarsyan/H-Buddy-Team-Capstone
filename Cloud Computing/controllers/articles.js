const axios = require('axios');
const cheerio = require('cheerio');
const delay = ms => new Promise(resolve => setTimeout(resolve, ms));

const herbArticles = [
    { herb: "Daun Jarak", url: "https://rumahpanduan.com/10-manfaat-daun-jarak-dan-cara-mengolahnya/", titleSelector: 'h1', dateSelector: '.entry-date' },
    { herb: "Daun Jeruk Nipis", url: "https://www.ilmupot.com/manfaat-daun-jeruk-nipis/", titleSelector: 'h1', dateSelector: '.entry-date' },
    { herb: "Kayu Putih", url: "https://asgar.or.id/health/makanan-dan-minuman-sehat/khasiat-obat-dan-manfaat-dari-kayu-putih/#:~:text=Cara%20Mengolah%20dan%20Meramu%20Kayu%20Putih%3A%201%201.,genggam%20...%204%204.%20Untuk%20Menyembuhkan%20Luka%20bernanah%3A", titleSelector: 'h1', dateSelector: '.entry-date' },
    { herb: "Daun Sirih", url: "https://artikel.rumah123.com/sederet-manfaat-daun-sirih-untuk-kesehatan-dan-cara-mengolahnya-52745", titleSelector: 'h1', dateSelector: '.entry-date' }
];

const scrapeArticles = async () => {
    const articles = [
        { title: "Bunga Asoka : Mitologi, Taksonomi, Morfologi, Ciri, Manfaat & Cara Tanam",
        publishDate: " ",
        url: "https://rimbakita.com/bunga-asoka/#:~:text=Caranya%20adalah%20dengan%20merebus%20bunga%20asoka%2C%20bunga%20mawar%2C,maksimal.%20Luka%20memar%20pun%20akan%20sembuh%20lebih%20cepat",
        description: "Tanaman asoka lebih dikenal sebagai tanaman hias karena bentuknya yang cantik dan warna bunga cerah. Tetapi, pohon asoka juga memiliki manfaat baik lainnya, terutama bagi kesehatan tubuh.",
        photoUrl: "https://rimbakita.com/wp-content/uploads/2020/08/bunga-asoka.jpg",
        herb: "Asoka"},
        { title: "12 Manfaat Bunga Telang untuk Kesehatan, Simak Cara Mengolahnya!",
        publishDate: " 08/1/2024 | 17:12 ",
        url: "https://mediaindonesia.com/humaniora/642780/12-manfaat-bunga-telang-untuk-kesehatan-simak-cara-mengolahnya#:~:text=12%20Manfaat%20Bunga%20Telang%20untuk%20Kesehatan%2C%20Simak%20Cara,8%208.%20Meningkatkan%20daya%20ingat%20...%20More%20items",
        description: "Manfaat bunga telang telah dikenal secara turun-menurun untuk membantu mengatasi beberapa penyakit, terutama bagi masyarakat di Indonesia.",
        photoUrl: "https://disk.mediaindonesia.com/thumbs/480x320/news/2024/01/1b98cb79989fb0a036d76c99bfe0e618.jpg",
        herb: "Bunga Telang"},
        { title: "7 Manfaat Daun Jambu Biji untuk Kesehatan dan Cara Mengonsumsi-Nya!",
        publishDate: " 2022 ",
        url: "https://www.gramedia.com/best-seller/manfaat-daun-jambu-biji/",
        description: "Tidak banyak orang tahu, kalau daun jambu biji juga menyehatkan dan memiliki banyak manfaat bagi tubuh kita. Menariknya, berbeda dengan buahnya, daun jambu biji lebih banyak dikonsumsi sebagai obat berbagai penyakit.",
        photoUrl: "https://cdnwpseller.gramedia.net/wp-content/uploads/2022/10/manfaat-daun-jambu-biji3-300x200.jpg",
        herb: "Daun Jambu Biji"},
        { title: "5 manfaat daun pepaya lengkap dan cara mengolahnya",
        publishDate: " 15 September 2021 ",
        url: "https://www.daya.id/usaha/artikel-daya/makan-sehat/5-manfaat-daun-pepaya-lengkap-dan-cara-mengolahnya",
        description: "Tak hanya buahnya saja, pada pepaya ternyata daunnya pun mengandung sejumlah hal baik untuk kita. Meski memang dikenal karena rasanya yang pahit, namun jika diolah dengan benar, bisa saja menjadikannya salah satu kuliner nusantara dengan cita rasa yang khas. Tak hanya dari rasanya saja, dengan mengkonsumsinya pula kita bisa mendapat sejumlah manfaat daun pepaya. Apa saja manfaat tersebut?",
        photoUrl: "https://www.daya.id/01%20Tips%20-%20Info%20Terkini/Kesehatan/2021/2021%20-%2008/33.%20Manfaat%20Daun%20Pepaya%20Lengkap%20dan%20Cara%20Mengolahnya/Traditional%20indonesian%20culinary%20food.%20Indonesian%20food%20Oseng%20oseng%20daun%20pepaya%20made%20from%20young%20Papaya_s%20leaf-%20mix%20with%20spice-%20chilli-%20with%20topping%20ikan%20Teri-%20Salted%20fried%20tiny%20fish.jpg",
        herb: "Daun Pepaya"},
        { title: "15 Manfaat Lidah Buaya untuk Kesehatan dan Cara Mengolahnya",
        publishDate: " Senin, 05 Sep 2022 | 12:08 WIB ",
        url: "https://www.detik.com/jabar/jabar-gaskeun/d-6273210/15-manfaat-lidah-buaya-untuk-kesehatan-dan-cara-mengolahnya",
        description: "Lidah buaya merupakan salah satu tanaman yang sering digunakan untuk obat herbal, perawatan kulit, sampai mengatasi masalah pencernaan. Tanaman ini memiliki segudang manfaat sehingga sangat cocok untuk berbagai kebutuhan kesehatanmu",
        photoUrl: "https://akcdn.detik.net.id/community/media/visual/2022/07/27/ilustrasi-manfaat-lidah-buaya-untuk-kecantikan_169.jpeg?w=700&q=90",
        herb: "Lidah Buaya"},
        { title: "7 Manfaat Daun Semanggi bagi Kesehatan dan Cara Mengolahnya",
        publishDate: " 12/12/2023, 20:30 WIB ",
        url: "https://www.kompas.com/tren/read/2023/12/12/203000465/7-manfaat-daun-semanggi-bagi-kesehatan-dan-cara-mengolahnya",
        description: "Semanggi yang memiliki empat kelopak daun dan sering dianggap sebagai simbol keberuntungan. Sementara bagi sebagian besar orang, semanggi umumnya dianggap gulma yang tumbuh di semak-semak pinggir jalan ataupun hama pengganggu tanaman di kebun. Namun sejumlah penelitian membuktikan daun semanggi memiliki banyak manfaat bagi kesehatan",
        photoUrl: "https://asset.kompas.com/crops/jdRzuWeFIhCsIFjUJbDykmYn6fg=/0x0:1280x853/750x500/data/photo/2023/06/08/648189002788b.jpg",
        herb: "Semanggi"},
    ];
    const promises = herbArticles.map(async (site) => {
        try {
            const { data } = await axios.get(site.url);
            const $ = cheerio.load(data);
            
            // Extracting the title with fallback
            let title = $(site.titleSelector).text().trim();
            if (!title) {
                title = $('title').text().trim();
                if (!title) {
                    console.error(`Title not found for ${site.url}`);
                }
            }
            
            // Extracting the publish date with fallback
            let publishDate = $(site.dateSelector).text().trim();
            if (!publishDate) {
                publishDate = $('time').first().text().trim();
                if (!publishDate) {
                    console.error(`Publish date not found for ${site.url}`);
                }
            }
            
            const url = site.url;
            
            // Extracting description with fallback
            let description = '';
            let photoUrl = '';
            $('meta').each((index, element) => {
                const property = $(element).attr('property');
                if (property === 'og:description' || $(element).attr('name') === 'description') {
                    description = $(element).attr('content');
                } else if (property === 'og:image') {
                    photoUrl = $(element).attr('content');
                }
            });
            
            if (!description) {
                description = $('p').first().text().trim();
                if (!description) {
                    console.error(`Description not found for ${site.url}`);
                }
            }
            
            if (!photoUrl) {
                photoUrl = $('img').first().attr('src');
                if (!photoUrl) {
                    console.error(`Photo URL not found for ${site.url}`);
                }
            }
            
            if (title && publishDate && url && description && photoUrl) {
                const article = { 
                    title, 
                    publishDate, 
                    url, 
                    description, 
                    photoUrl,
                    herb: site.herb 
                };
                articles.push(article);
            } else {
                console.error(`Missing data for ${site.url}`);
            }
        } catch (error) {
            console.error(`Error scraping ${site.herb}:`, error);
        }
    });
    
    await Promise.all(promises);
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
