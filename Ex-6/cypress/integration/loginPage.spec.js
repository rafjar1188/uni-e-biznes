
describe("Sign in page", () => {

    beforeEach(() => {
        cy.visit("http://localhost:3000");
    })

    it("Renders correctly", () => {

        cy.get("form").should("have.attr", "autocomplete")
            .and("eq", "off")

        cy.get("input[name=username]").should(input => {
            expect(input).to.have.attr("required");
            expect(input).to.have.attr("minlength", "4");
            expect(input).to.have.attr("maxlength", "16");
        })

        cy.get("input[name=password]").should(input => {
            expect(input).to.have.attr("required");
            expect(input).to.have.attr("minlength", "8");
            expect(input).to.have.attr("maxlength", "24");
        })

        cy.get("button[type=submit]").should("exist")
        // cy.get("button[type=submit]").should("contain.text", "ZALOGUJ")
    })

    it('Empty data set', () => {
        cy.get("form").within(() => {
            cy.get("input:invalid").should("have.length", 2)
        })
    });

    it('One input filled', () => {
        cy.get("form").within(() => {
            //username only
            cy.get("input[name=username]").type("test");
            cy.get("input:invalid").should("have.length", 1);

            cy.get("input[name=username]").clear();
            cy.get("input[name=password]").type("12345678");
            cy.get("input:invalid").should("have.length", 1);
        })
    });

    it('Sign in failure', function () {
        cy.get("input[name=username]").type("test");
        cy.get("input[name=password]").type("12345678");
        cy.get("button[type=submit]").click()

        cy.contains("p", "Nieprawidłowa nazwa użytkownika lub hasło").should("exist")
    });

    it('Sign in success', function () {
        cy.get("input[name=username]").type("test");
        cy.get("input[name=password]").type("testtest");
        cy.get("form").submit();
        cy.url().should("contain", "/orders");
    });

})
