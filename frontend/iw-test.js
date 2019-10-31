import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@vaadin/vaadin-login/src/vaadin-login-form.js';

class IwTest extends PolymerElement {

    static get template() {
        return html`
<style include="shared-styles">
                :host {
                    display: block;
                    height: 100%;
                }
            </style>
<vaadin-vertical-layout style="width: 100%; height: 100%;">
 <vaadin-login-form error></vaadin-login-form>
</vaadin-vertical-layout>
`;
    }

    static get is() {
        return 'iw-test';
    }

    static get properties() {
        return {
            // Declare your properties here.
        };
    }
}

customElements.define(IwTest.is, IwTest);
