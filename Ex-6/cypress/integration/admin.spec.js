
describe("Admin panel", () => {
    beforeEach(() => {
        cy.visit("http://localhost:3000/admin_panel");
        cy.get("input[name=username]").type("test");
        cy.get("input[name=password]").type("testtest");
        cy.get("form").submit();
    });

    it('Open new user modal', function () {
        cy.get("form").should("not.exist");
        cy.contains("button", "Dodaj użytnownika").click();
        cy.get("form").should("exist");
    });


    it('Open new role modal', function () {
        cy.contains("button", "Uprawnienia").click();
        cy.contains("button", "Dodaj uprawnienie").click();
        cy.get("form").should("exist");
    });
})
