let users = 0
describe("Products Page", () => {
    beforeEach(() => {
        cy.visit("http://localhost:3000/products");
        cy.get("input[name=username]").type("test");
        cy.get("input[name=password]").type("testtest");
        cy.get("form").submit();
    });

    it('Render correctly', function () {
        cy.contains("button", "Dodaj produkt").should("exist");
        cy.contains("Produkty").should("exist");
        cy.get("thead th").should("have.length", 4)
        cy.get("thead th").first().should("have.text", "Kod produktu")
        cy.get("thead th").last().should("have.text", "Cena")
    });

    it("Open new product modal", () => {
        cy.contains("button", "Dodaj produkt").click();
        cy.get("form").should("exist");
    });

    it('Close new product modal by button', function () {
        cy.contains("button", "Dodaj produkt").click();
        cy.get("form").should("exist")
        cy.get("form").find("button:last").click()
        cy.get("form").should("not.exist")
    });

    it('Close new product modal by x', function () {
        cy.contains("button", "Dodaj produkt").click();
        cy.get("form").should("exist")
        cy.get("form").find("svg").click()
        cy.get("form").should("not.exist")
    });

    it('Close new product modal by backdrop', function () {
        cy.contains("button", "Dodaj produkt").click();
        cy.get("form").should("exist")
        cy.get("body").click(50, 50)
        cy.get("form").should("not.exist")
    });

    it("New product form correct", () => {
        cy.contains("button", "Dodaj produkt").click();
        cy.get("input[name=code]").should("have.attr", "required");
        cy.get("input[name=name]").should("have.attr", "required");
        cy.get("input[name=basePrice]").should("have.attr", "required");
        cy.get("input[name=basePrice]").should("have.attr", "type", "number");
    });


});
