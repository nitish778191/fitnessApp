for tag in Tags:
    body_html_table += """
    <tr>
        <td colspan="4">
            <details>
                <summary>Tags Info:</summary>
                <table>
                    <tr>
                        <td>Tags-Name: <p>{}</p></td>
                        <td>Tags-Category: <p>{}</p></td>
                        <td>Tags-Intention: <p>{}</p></td>
                        <td>Tag-Description: <p>{}</p></td>
                    </tr>
                </table>
            </details>
        </td>
    </tr>
    """.format(
        str(tag['name']),
        str(tag['category']),
        str(tag['intention']),
        str(tag['description'])
    )
