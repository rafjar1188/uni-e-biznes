let objects = 0;
describe("Customer details", () => {
   before(() => {
       cy.visit("http://localhost:3000/customers");
       cy.get("input[name=username]").type("test");
       cy.get("input[name=password]").type("testtest");
       cy.get("form").submit();
       cy.get("tbody a").first().click();

       cy.get("#root > div.MuiContainer-root.MuiContainer-maxWidthLg > div > form > div.MuiCardContent-root > div:nth-child(8) div.MuiGrid-root.MuiGrid-item.MuiGrid-grid-xs-4").its("length").then(n => objects = n)

   });

    it('Render correctly', function () {
        cy.get("input[name=email]").should("have.value", "klient1@asd.pl");
        cy.get("input[name=nip]").should("have.value", "1234567890");
        cy.get("input[name=phone]").should("have.value", "147852369");
        cy.get("input[name='address.street']").should("have.value", "Akacjowa");
        cy.get("input[name='address.city']").should("have.value", "Poznań");

        cy.get("#root > div.MuiContainer-root.MuiContainer-maxWidthLg > div > form > div.MuiCardContent-root > div:nth-child(8)").as("objectsContainer");
        cy.get("@objectsContainer").should("exist")
        cy.get("#root > div.MuiContainer-root.MuiContainer-maxWidthLg > div > form > div.MuiCardContent-root > div:nth-child(8) div.MuiGrid-root.MuiGrid-item.MuiGrid-grid-xs-4").should("have.length", 3)
    });

    it("Add new object", () => {
        cy.get("#root > div.MuiContainer-root.MuiContainer-maxWidthLg > div > form > div.MuiCardContent-root > div:nth-child(8) div.MuiGrid-root.MuiGrid-item.MuiGrid-grid-xs-4").as("objects");
        cy.get("#root > div.MuiContainer-root.MuiContainer-maxWidthLg > div > form > div.MuiCardContent-root > div.MuiCollapse-container").as("collapse");

        cy.get("@objects").should("have.length", objects);
        cy.get("@collapse").should("have.class", "MuiCollapse-hidden");
        cy.contains("button", "Nowy obiekt").click();
        cy.get("@collapse").should("not.have.class", "MuiCollapse-hidden");

        cy.get("input[name=street]").type("Testowa");
        cy.get("input[name=houseNumber]").type("10");
        cy.get("input[name=city]").type("Berlin");
        cy.contains("Dodaj obiekt").click();
        cy.get("@objects").should("have.length", objects + 1).its("length").then(n => objects = n);
    });

});
