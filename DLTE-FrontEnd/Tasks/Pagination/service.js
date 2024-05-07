const cardData = {
    "19876543219870": [{
            "debitCardNumber": "1111 2222 3333 4444",
            "customerId": "001",
            "debitCardCvv": "123",
            "debitCardPin": "5678",
            "debitCardStatus": "Active",
            "domesticLimit": 1000,
            "internationalLimit": 5000
        },
        {
            "debitCardNumber": "5555 6666 7777 8888",
            "customerId": "001",
            "debitCardCvv": "456",
            "debitCardPin": "9012",
            "debitCardStatus": "Block",
            "domesticLimit": 1500,
            "internationalLimit": 7500
        },
        {
            "debitCardNumber": "2222 4343 5678 1234",
            "customerId": "001",
            "debitCardCvv": "246",
            "debitCardPin": "1234",
            "debitCardStatus": "Block",
            "domesticLimit": 200,
            "internationalLimit": "9800"
        },
        {
            "debitCardNumber": "2222 4343 5678 1234",
            "customerId": "001",
            "debitCardCvv": "246",
            "debitCardPin": "1234",
            "debitCardStatus": "Block",
            "domesticLimit": 3000,
            "internationalLimit": "9800"
        },
        {
            "debitCardNumber": "2222 4343 5678 1234",
            "customerId": "001",
            "debitCardCvv": "246",
            "debitCardPin": "1234",
            "debitCardStatus": "Block",
            "domesticLimit": 1000,
            "internationalLimit": "9800"
        },
        {
            "debitCardNumber": "2222 4343 5678 1234",
            "customerId": "001",
            "debitCardCvv": "246",
            "debitCardPin": "1234",
            "debitCardStatus": "Block",
            "domesticLimit": 2000,
            "internationalLimit": "9800"
        },
        {
            "debitCardNumber": "2222 4343 5678 1234",
            "customerId": "001",
            "debitCardCvv": "246",
            "debitCardPin": "1234",
            "debitCardStatus": "Block",
            "domesticLimit": 900,
            "internationalLimit": "9800"
        }
    ],
    "17896570987961": [{
        "debitCardNumber": "9999 0000 1111 2222",
        "customerId": "002",
        "debitCardCvv": "789",
        "debitCardPin": "3456",
        "debitCardStatus": "Active",
        "domesticLimit": 2000,
        "internationalLimit": 1000
    },
    {
        "debitCardNumber": "9999 0000 1111 2222",
        "customerId": "002",
        "debitCardCvv": "789",
        "debitCardPin": "3456",
        "debitCardStatus": "Block",
        "domesticLimit": 2000,
        "internationalLimit": 1000
    }
]
};

const itemsPerPage = 2;
let currentPage = 1;

function searchCards() {
    const accountNumber = document.getElementById('accountNumber').value.trim();
    const domesticLimit = parseInt(document.getElementById('domesticLimit').value, 10);
    if (!accountNumber || !domesticLimit) {
        const cardListContainer = document.getElementById('cardList');
        cardListContainer.innerHTML = '<p class="text-danger">Please enter account number and domestic limit.</p>';
        document.getElementById('pagination').innerHTML = '';
        cardListContainer.style.display = 'none';
        return;
    }
    const cardListContainer = document.getElementById('cardList');
    cardListContainer.innerHTML = '';
    


    if (accountNumber in cardData) {
        const cards = cardData[accountNumber];
        const filteredCards = cards.filter(card => card.domesticLimit > domesticLimit);

        if (filteredCards.length > 0) {
            const startIndex = (currentPage - 1) * itemsPerPage;
            const paginatedCards = filteredCards.slice(startIndex, startIndex + itemsPerPage);

            paginatedCards.forEach((card, index) => {
                const cardHtml = `
                    
                        <div class="card  col-lg-4 col-md-3 p-3 rounded-5 m-4 border-primary shadow">
                            <div class="card-body">
                                <h5 class="card-title text-center">${card.debitCardNumber}</h5>
                                <p class="card-text text-center">Customer ID: ${card.customerId}</p>
                                <p class="card-text text-center">Domestic Limit: ${card.domesticLimit}</p>
                                <button class="btn btn-primary text-center" onclick="showCardDetails('${card.debitCardNumber}', 
                                '${card.customerId}', 
                                '${card.debitCardCvv}', 
                                '${card.debitCardPin}',
                                 '${card.debitCardStatus}', 
                                 '${card.domesticLimit}',
                                  '${card.internationalLimit}')">
                                  View Details
                                  </button>
                            </div>
                        </div>
                    
                    
                `;
                cardListContainer.innerHTML += cardHtml;
            });

            renderPagination(filteredCards.length);
            cardListContainer.style.display = 'flex';
        } else {
            cardListContainer.innerHTML = '<p class="text-danger">No debit cards found for this account number with the specified domestic limit.</p>';
            document.getElementById('pagination').innerHTML = '';
            cardListContainer.style.display = 'none';
        }
    } else {
        cardListContainer.innerHTML = '<p class="text-danger">No debit cards found for this account number.</p>';
        document.getElementById('pagination').innerHTML = '';
        cardListContainer.style.display = 'none';
    }
}

function renderPagination(totalItems) {
    const totalPages = Math.ceil(totalItems / itemsPerPage);
    const paginationContainer = document.getElementById('pagination');
    paginationContainer.innerHTML = '';

    for (let i = 1; i <= totalPages; i++) {
        const liClass = (i === currentPage) ? 'page-item active' : 'page-item';
        const paginationHtml = `
            <li class="${liClass}">
                <a class="page-link" href="#" onclick="changePage(${i})">${i}</a>
            </li>
        `;
        paginationContainer.innerHTML += paginationHtml;
    }
}

function changePage(page) {
    currentPage = page;
    searchCards();
}



function showCardDetails(debitCardNumber, customerId, debitCardCvv, debitCardPin, debitCardStatus, domesticLimit, internationalLimit) {
    const cardTitle = `Debit Card Details - ${debitCardNumber}`;
    const cardDetails = `
<p><strong>Customer ID:</strong> ${customerId}</p>
<p><strong>CVV:</strong> ${debitCardCvv}</p>
<p><strong>PIN:</strong> ${debitCardPin}</p>
<p><strong>Status:</strong> ${debitCardStatus}</p>
<p><strong>Domestic Limit:</strong> ${domesticLimit}</p>
<p><strong>International Limit:</strong> ${internationalLimit}</p>
`;

    const titleElement = document.getElementById('title');
    const detailsElement = document.getElementById('details');

    if (titleElement && detailsElement) {
        titleElement.innerText = cardTitle;
        detailsElement.innerHTML = cardDetails;

        const modal = new bootstrap.Modal(document.getElementById('cardDetailsModal'));
        modal.show();
    }
}