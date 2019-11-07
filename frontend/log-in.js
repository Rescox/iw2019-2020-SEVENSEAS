import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/vaadin-login/src/vaadin-login-form.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';

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
 <vaadin-horizontal-layout>
  <vaadin-vertical-layout theme="spacing"></vaadin-vertical-layout>
  <vaadin-login-form id="vaadinLoginForm" style="right: 50%"></vaadin-login-form>
 </vaadin-horizontal-layout>
</vaadin-vertical-layout>
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
