body_html_table += (
    "<tr><td align=\"left\"; valign=\"top\">"
    + "<b> First_seen :</b> " + str(first_seen)
    + "<b> Last-Seen:</b> " + str(last_seen)
    + " <b>Seen:</b> " + str(seen)
    + "<b> Actor:</b> " + str(actor)
    + "<b> Spoofable: </b>" + str(spoof)
    + "<b> Classification: </b>" + str(classi)
    + "<b> Bot:</b> " + str(bot)
    + "<b> VPN Service:</b> " + str(vpn_service)
    + "<b> VPN: </b>" + str(vpn)
    + "<br>"
    + "<b>CVE: </b> "
    + "<details><summary>Click to view CVE data</summary>" + str(cve) + "</details>"
    + "</td></tr><br>"
)
