import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@vaadin/vaadin-button/src/vaadin-button.js';
import '@vaadin/vaadin-form-layout/src/vaadin-form-item.js';
import '@vaadin/vaadin-text-field/src/vaadin-text-field.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-horizontal-layout.js';

class RegForm extends PolymerElement {

    static get template() {
        return html`
<style include="shared-styles">
                :host {
                    display: block;
                    height: 100%;
                }
            </style>
<vaadin-horizontal-layout style="width: 20%; height: 20%;"></vaadin-horizontal-layout>
<vaadin-horizontal-layout style="width: 100%; height: 100%;">
 <vaadin-vertical-layout style="width: 50%; height: 100%;"></vaadin-vertical-layout>
 <vaadin-vertical-layout id="vaadinVerticalLayout" style="width: 100%; height: 100%; text-align:center;">
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
 <vaadin-vertical-layout style="width: 50%; height: 100%;"></vaadin-vertical-layout>
</vaadin-horizontal-layout>
`;
    }

    static get is() {
        return 'reg-form';
    }

    static get properties() {
        return {
            // Declare your properties here.
        };
    }
}

customElements.define(RegForm.is, RegForm);
