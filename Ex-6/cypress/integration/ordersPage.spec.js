
describe("Orders Page", () => {

    beforeEach(() => {
        cy.visit("http://localhost:3000");
        cy.get("input[name=username]").type("test");
        cy.get("input[name=password]").type("testtest");
        cy.get("form").submit();
    });

    it("Renders correctly", () => {
        cy.get("header a").should("have.length", 4)

        cy.get("a[href*=orders]").should("have.text", "Umowy");
        cy.get("a[href*=products]").should("have.text", " Produkty ");
        cy.get("a[href*=customers]").should("have.text", " Klienci ");

        cy.contains("button", "test").should("exist");
        cy.contains("button", "Dodaj Umowę").should("exist");

        cy.get("thead th").should("have.length", 7).first().should("have.text", "Numer umowy")
        cy.get("thead th").last().should("have.text", "Spółka")
    });

    // it("Open new order modal", () => {
    //     cy.get("button#addOrderModal").click()
    //     cy.get("form").should("exist");
    // });
    //
    // it("New order - empty set", () => {
    //     cy.get("button#addOrderModal").click()
    //     cy.get("form").within(() => {
    //         cy.get("input:invalid").should("have.length", 3);
    //     });
    //
    // });


});
