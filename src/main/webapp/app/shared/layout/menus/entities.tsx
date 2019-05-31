import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  // tslint:disable-next-line:jsx-self-close
  <NavDropdown icon="th-list" name="Entities" id="entity-menu">
    <MenuItem icon="asterisk" to="/entity/list-object-status">
      List Object Status
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/pipeline-section">
      Pipeline Section
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/free-span-history">
      Free Span History
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/anode">
      Anode
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/anode-hist">
      Anode Hist
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/base-class">
      Base Class
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/bend">
      Bend
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/bend-hist">
      Bend Hist
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/buckle-arrestor">
      Buckle Arrestor
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/buckle-arrestor-hist">
      Buckle Arrestor Hist
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/cps">
      Cps
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/cps-hist">
      Cps Hist
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/cps-range">
      Cps Range
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/displacement">
      Displacement
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/displacement-hist">
      Displacement Hist
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/free-span">
      Free Span
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/free-span-hist">
      Free Span Hist
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/free-span-support">
      Free Span Support
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/free-span-support-hist">
      Free Span Support Hist
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/launch-receiver">
      Launch Receiver
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/launch-receiver-hist">
      Launch Receiver Hist
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-anode-bracelete-type">
      List Anode Bracelete Type
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-bend-manufacturer">
      List Bend Manufacturer
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-bend-specification">
      List Bend Specification
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-bend-type">
      List Bend Type
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-boundary-cond-ucase">
      List Boundary Cond Ucase
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-bucklarr-manufacturer">
      List Bucklarr Manufacturer
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-bucklarr-specification">
      List Bucklarr Specification
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-bucklarr-type">
      List Bucklarr Type
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-clay-type">
      List Clay Type
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-clc-kind">
      List Clc Kind
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-clc-lvl">
      List Clc Lvl
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-clc-result">
      List Clc Result
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-clc-type">
      List Clc Type
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-dfct-group">
      List Dfct Group
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-dfct-pos-type">
      List Dfct Pos Type
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-dfct-type">
      List Dfct Type
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-eff-axforce-ucase">
      List Eff Axforce Ucase
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-env-direction">
      List Env Direction
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-env-point">
      List Env Point
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-external-coating">
      List External Coating
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-fabrication-type">
      List Fabrication Type
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-ili-pig-type">
      List Ili Pig Type
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-internal-coating">
      List Internal Coating
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-internal-press-ucase">
      List Internal Press Ucase
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-long-seam-weld-type">
      List Long Seam Weld Type
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-material">
      List Material
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-mill-location">
      List Mill Location
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-minpress-ucase">
      List Minpress Ucase
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-nominal-wall-thickness">
      List Nominal Wall Thickness
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-object-type">
      List Object Type
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-pipe-manufacturer">
      List Pipe Manufacturer
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-pipe-specification">
      List Pipe Specification
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-pipejoint-specification">
      List Pipejoint Specification
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-pipejoint-type">
      List Pipejoint Type
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-pipeline-location">
      List Pipeline Location
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-risk-consequence">
      List Risk Consequence
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-risk-probability">
      List Risk Probability
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-safety-class">
      List Safety Class
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-sand-type">
      List Sand Type
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-soil-type">
      List Soil Type
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-steel-grade">
      List Steel Grade
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-tee-manufacturer">
      List Tee Manufacturer
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-tee-specification">
      List Tee Specification
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-tee-type">
      List Tee Type
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-threat">
      List Threat
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-threat-group">
      List Threat Group
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-valve-function">
      List Valve Function
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-valve-manufacturer">
      List Valve Manufacturer
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-valve-specification">
      List Valve Specification
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-valve-type">
      List Valve Type
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-wrk-kind">
      List Wrk Kind
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-wrk-purpose">
      List Wrk Purpose
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-wrk-status">
      List Wrk Status
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/list-wrkcmms-status">
      List Wrkcmms Status
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/meta-list">
      Meta List
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/pipe">
      Pipe
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/pipe-hist">
      Pipe Hist
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/pipejoint">
      Pipejoint
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/pipejoint-hist">
      Pipejoint Hist
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/pipeline">
      Pipeline
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/pipeline-hist">
      Pipeline Hist
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/pipeline-segment">
      Pipeline Segment
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/tee">
      Tee
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/tee-hist">
      Tee Hist
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/valve">
      Valve
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/valve-hist">
      Valve Hist
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
