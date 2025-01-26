describe('Geld over maken naar rekening', () => {
    beforeEach(()=>{
        cy.visit('http://localhost:3000/')
        cy.get(".login__account").first().click();
    });

    it('Normale overboeking', () => {
        cy.visit('http://localhost:3000/transfer');
        cy.get('.amount-input').type(100);
        cy.get('.to-account').select("Sophie de Blaak");
        cy.get("textarea").type("Dit is een overboeking");
        cy.get("[data-test-id='overboeken-button']").click();
        cy.get('.alert').should("be.visible");
    })

    it('overboeking zonder geld', () => {
        cy.visit('http://localhost:3000/transfer');
        cy.get("textarea").type("Dit is een overboeking zonder geld");
        cy.get('.amount-input').type(100000000);
        cy.get('.to-account').select("Sophie de Blaak");
        cy.get("[data-test-id='overboeken-button']").click();
        cy.get('.alert').should("be.visible");
    })

    it('overboeking met ander valuta', () => {
        cy.visit('http://localhost:3000/transfer');
        cy.get('.to-account').select("Sophie de Blaak");
        cy.get('.currency-input').first().click();
        cy.get('.amount-input').type(100);
        cy.get("textarea").type("Dit is een overboeking met een andere valuta");
        cy.get("[data-test-id='overboeken-button']").click();
        cy.get('.alert').should("be.visible");
    })
})