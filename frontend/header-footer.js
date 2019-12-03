import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-horizontal-layout.js';
import '@polymer/iron-icon/iron-icon.js';
import '@vaadin/vaadin-button/src/vaadin-button.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';

class HeaderFooter extends PolymerElement {

    static get template() {
        return html`
<style include="shared-styles">
                :host {
                    display: block;
                    height: 100%;
                }
            </style>
<vaadin-vertical-layout style="width: 100%; height: 100%;">
 <vaadin-horizontal-layout class="header" style="width: 100%; min-height: var(--lumo-size-l); background-color: #baf7f7" id="header">
  <span style="width: 250px">Seven seas software </span>
  <vaadin-button style="right: -60%;" class="loginbutton" id="login">
   <iron-icon icon="lumo:user" slot="prefix"></iron-icon>Login 
  </vaadin-button>
  <vaadin-button style="right: -60%;" class="registerbutton" id="register">
   <iron-icon icon="lumo:edit" slot="prefix"></iron-icon>Register 
  </vaadin-button>
 </vaadin-horizontal-layout>
 <vaadin-vertical-layout style="width: 100%; height: 100%; text-align:center;">
  <vaadin-form-item>
   <label slot="label">Username</label>
   <vaadin-text-field class="full-width" value="Jane" required></vaadin-text-field>
  </vaadin-form-item>
  <vaadin-form-item>
   <label slot="label">First Name</label>
   <vaadin-text-field class="full-width" value="Jane"></vaadin-text-field>
  </vaadin-form-item>
  <vaadin-form-item>
   <label slot="label">Last Name</label>
   <vaadin-text-field class="full-width" value="Doe"></vaadin-text-field>
  </vaadin-form-item>
  <vaadin-form-item>
   <label slot="label">Email</label>
   <vaadin-text-field class="full-width" value="jane.doe@example.com"></vaadin-text-field>
  </vaadin-form-item>
  <vaadin-form-item>
   <label slot="label">Address</label>
   <vaadin-text-field class="full-width" value="Some Street 123"></vaadin-text-field>
  </vaadin-form-item>
  <vaadin-form-item>
   <vaadin-button theme="primary">
     Submit 
   </vaadin-button>
  </vaadin-form-item>
 </vaadin-vertical-layout>
 <vaadin-horizontal-layout class="footer" style="width: 100%; min-height: var(--lumo-size-l); background-color: #baf7f7" id="footer">
  <vaadin-button theme="icon" aria-label="Add new">
   <span style="width: 250px">About us </span>
  </vaadin-button>
 </vaadin-horizontal-layout>
</vaadin-vertical-layout>
`;
    }

    static get is() {
        return 'header-footer';
    }

    static get properties() {
        return {
            // Declare your properties here.
        };
    }
}

customElements.define(HeaderFooter.is, HeaderFooter);
