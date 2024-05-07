const cardData = {
    "17896570987961": [
        { "debitCardNumber": "1111 2222 3333 4444", "customerId": "001", "debitCardCvv": "123", "debitCardPin": "5678", "debitCardStatus": "Active", "domesticLimit": "1000", "internationalLimit": "5000" },
        { "debitCardNumber": "5555 6666 7777 8888", "customerId": "001", "debitCardCvv": "456", "debitCardPin": "9012", "debitCardStatus": "Block", "domesticLimit": "1500", "internationalLimit": "7500" },
        { "debitCardNumber": "2222 4343 5678 1234", "customerId": "001", "debitCardCvv": "246", "debitCardPin": "1234", "debitCardStatus": "Block", "domesticLimit": "5600", "internationalLimit": "9800" },
        { "debitCardNumber": "2222 4343 5678 1234", "customerId": "001", "debitCardCvv": "246", "debitCardPin": "1234", "debitCardStatus": "Block", "domesticLimit": "5600", "internationalLimit": "9800" },
        { "debitCardNumber": "2222 4343 5678 1234", "customerId": "001", "debitCardCvv": "246", "debitCardPin": "1234", "debitCardStatus": "Block", "domesticLimit": "5600", "internationalLimit": "9800" },
        { "debitCardNumber": "2222 4343 5678 1234", "customerId": "001", "debitCardCvv": "246", "debitCardPin": "1234", "debitCardStatus": "Block", "domesticLimit": "5600", "internationalLimit": "9800" },
        { "debitCardNumber": "2222 4343 5678 1234", "customerId": "001", "debitCardCvv": "246", "debitCardPin": "1234", "debitCardStatus": "Block", "domesticLimit": "5600", "internationalLimit": "9800" }],
    
    "19876543219870": [
        { "debitCardNumber": "9999 0000 1111 2222", "customerId": "002", "debitCardCvv": "789", "debitCardPin": "3456", "debitCardStatus": "Active", "domesticLimit": "2000", "internationalLimit": "1000" }
    ]
};

function searchCards() {
    const accountNumber = document.getElementById('accountNumber').value.trim();
    const cardListContainer = document.getElementById('cardList');
    cardListContainer.innerHTML = ''; 

    if (accountNumber in cardData) {
        const cards = cardData[accountNumber];
        cards.forEach((card, index) => {
            const cardHtml = `
            <br/>
                <div class="col-lg-4 col-md-6 mb-4 d-flex ">
                    <div class="card border-warning p-3  mx-auto text-no-wrap justify-content-center shadow-sm" onclick="showCardDetails('${card.debitCardNumber}', '${card.customerId}', '${card.debitCardCvv}', '${card.debitCardPin}', '${card.debitCardStatus}', '${card.domesticLimit}', '${card.internationalLimit}')">
                        <div class="card-body">
                            <h5 class="card-title text-primary">${card.debitCardNumber}</h5>
                            <p class="card-text">Customer ID: ${card.customerId}</p>
                        </div>
                    </div>
                </div>
            `;
            cardListContainer.innerHTML += cardHtml;
        });
    } else {
        cardListContainer.innerHTML = '<p class="text-danger">No debit cards found for this account number.</p>';
    }
}

function showCardDetails(debitCardNumber, customerId, debitCardCvv, debitCardPin, debitCardStatus, domesticLimit, internationalLimit) {
    const cardTitle = `${debitCardNumber}`;
    const cardDetails = `
        <p><strong>Customer ID:</strong> ${customerId}</p>
        <p><strong>CVV:</strong> ${debitCardCvv}</p>
        <p><strong>PIN:</strong> ${debitCardPin}</p>
        <p><strong>Status:</strong> ${debitCardStatus}</p>
        <p><strong>Domestic Limit:</strong> ${domesticLimit}</p>
        <p><strong>International Limit:</strong> ${internationalLimit}</p>
    `;


    const TitleElement = document.getElementById('title');
    const BodyElement = document.getElementById('details');

    TitleElement.innerText = cardTitle;
    BodyElement.innerHTML = cardDetails;

    
    const modal = new bootstrap.Modal(document.getElementById('cardDetailsModal'));
    modal.show();
}
