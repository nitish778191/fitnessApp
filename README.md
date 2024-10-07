{% set ns = namespace(change_incident_closure_info=[], field_json={}) %}

{% set ns.field_json = {"Closure Code": "2", "Playbook Closure Reason": (PlaybookData | jsonpath('$.DataSource.commandInput.closure_reason')), "Playbook Closure Notes": PlaybookData | jsonpath('$.DataSource.commandInput.closure_notes'), "Escalated To": PlaybookData | jsonpath('$.DataSource.commandInput.escalated_to'), "Escalation Date":PlaybookData | jsonpath('$.["Get Current Date and Time"].returnData')} %}

{% if (PlaybookData | jsonpath('$.["Get Linked Incidents"].returnData') is iterable) and (PlaybookData | jsonpath('$.["Get Linked Incidents"].returnData') is not string): %}
    {% for incident_no in PlaybookData | jsonpath('$.["Get Linked Incidents"].returnData'): %}
        {% if (incident_no is iterable) and (incident_no is not string): %}
            {% if ns.field_json["Playbook Closure Reason"] is none or ns.field_json["Playbook Closure Reason"]=="" %}
                {% set ns.field_json={
        "Playbook Closure Reason": "Duplicate"
        } %}
                {% set ns.change_incident_closure_info = ns.change_incident_closure_info + [ {"IncidentNumber": incident_no[0], "SectionName": "Playbook Closure Information", "FieldValue": ns.field_json} ] %}

            
              
            {% else %}
                    {%set ns.field_json =ns.field_json %}
                    {% set ns.change_incident_closure_info = ns.change_incident_closure_info + [ {"IncidentNumber": incident_no[0], "SectionName": "Playbook Closure Information", "FieldValue": ns.field_json} ] %}
            {% endif %}
            
                
            
        {% else %}
            {% if ns.field_json["Playbook Closure Reason"] is none or ns.field_json["Playbook Closure Reason"]=="" %}
                    {% set ns.field_json={
            "Playbook Closure Reason": "Duplicate"
            } %}
                    {% set ns.change_incident_closure_info = ns.change_incident_closure_info + [ {"IncidentNumber": incident_no[0], "SectionName": "Playbook Closure Information", "FieldValue": ns.field_json} ] %}

                
              
            {% else %}
                    {%set ns.field_json =ns.field_json %}
                    {% set ns.change_incident_closure_info = ns.change_incident_closure_info + [ {"IncidentNumber": incident_no[0], "SectionName": "Playbook Closure Information", "FieldValue": ns.field_json} ] %}
            {% endif %}
        {% endif %}
    {% endfor %}
{% else %}
    {% set ns.change_incident_closure_info = ns.change_incident_closure_info + [ {"IncidentNumber": PlaybookData | jsonpath('$.["Get Linked Incidents"].returnData'), "SectionName": "Playbook Closure Information", "FieldValue": ns.field_json} ] %}
{% endif %}

{{ ns.change_incident_closure_info }}
