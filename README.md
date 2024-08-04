body_html_table = """
<details>
    <summary>Show User-Agents</summary>
    <table border="1">
        <tbody>
"""

for ua in Raw_Data_Web_ua:
    body_html_table += f"<tr><td>User-Agent: {str(ua)}</td></tr>"

body_html_table += """
        </tbody>
    </table>
</details>
"""

# Now you can insert body_html_table into your HTML template
