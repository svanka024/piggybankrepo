describe('naam van rekening houder veranderen', () => {
    beforeEach(() => {
        cy.visit('http://localhost:3000/')
        cy.get(".login__account").first().click();
    });

    it('naam veranderen', () => {
        cy.visit('http://localhost:3000/settings');
        cy.get('.name-input').type("rekening van kees");
        cy.get("[data-test-id='save-button']").click();
        cy.get('.alert').should("be.visible");
    })
})