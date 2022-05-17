let customers = 0

describe("Customers", () => {
    beforeEach(() => {
        cy.visit("http://localhost:3000/customers");
        cy.get("input[name=username]").type("test");
        cy.get("input[name=password]").type("testtest");
        cy.get("form").submit();

        cy.get("tbody a")
            .its("length")
            .then(n => customers = n)
    });

    it('Render correct', function () {
        cy.get("thead th").should("have.length", 4)
    });

    it('Open new customer modal', function () {
        cy.contains("Dodaj klienta").click();
        cy.get("form").should("exist");
    });

    it('Close new customer modal', function () {
        cy.contains("Dodaj klienta").click();
        cy.get("form").find("button").last().click();
        cy.get("form").should("not.exist");
    });

    it('Add customer - failure', function () {
        cy.contains("Dodaj klienta").click();
        cy.get("button[type=submit]").click();
        cy.get("input[name=name]").should("have.attr", "aria-invalid", "To pole jest wymagane");
        // cy.get("form").within((form) => {
        //     form.find("button:first").click()
        //     cy.get("input:invalid").should("have.length", 1)
        // })
    });

    it('Add customer minimal data', function () {
        cy.contains("Dodaj klienta").click();
        cy.get("input[name=name]").type("Test")
        cy.get("button[type=submit]").click();

        cy.get("tbody a").should("have.length", customers + 1).its(length).then(n => customers = n);

    });

})
