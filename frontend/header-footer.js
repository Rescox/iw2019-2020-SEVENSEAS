import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/vaadin-button/src/vaadin-button.js';
import '@polymer/iron-icon/iron-icon.js';

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
 <vaadin-horizontal-layout class="header" style="width: 100%; min-height: var(--lumo-size-l); background-color: #baf7f7">
  <span style="width: 250px">Seven seas software </span>
  <vaadin-button style="right: -60%;" class="loginbutton">
   <iron-icon icon="lumo:user" slot="prefix"></iron-icon>Login 
  </vaadin-button>
 </vaadin-horizontal-layout>
 <vaadin-vertical-layout class="content" style="width: 100%; min-height: 0; flex: 1;"></vaadin-vertical-layout>
 <vaadin-horizontal-layout class="footer" style="width: 100%; min-height: var(--lumo-size-l); background-color: #baf7f7">
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
