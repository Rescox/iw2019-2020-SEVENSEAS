import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@vaadin/vaadin-login/src/vaadin-login-form-wrapper.js';
import '@vaadin/vaadin-login/src/vaadin-login-form.js';

class LogIn extends PolymerElement {

    static get template() {
        return html`
<style include="shared-styles">
                :host {
                    display: block;
                    height: 100%;
                }
            </style>
<vaadin-vertical-layout style="width: 100%; height: 100%;">
 <vaadin-login-form></vaadin-login-form>
</vaadin-vertical-layout>
<vaadin-login-form-wrapper></vaadin-login-form-wrapper>
`;
    }

    static get is() {
        return 'log-in';
    }

    static get properties() {
        return {
            // Declare your properties here.
        };
    }
}

customElements.define(LogIn.is, LogIn);
