def flatten_and_clean(ip_list):
    cleaned_ip_list = []
    for item in ip_list:
        # Flatten nested lists and ensure the item is a string
        if isinstance(item, list):
            item = item[0]
        # Strip any surrounding quotes if item is a string
        if isinstance(item, str):
            cleaned_ip_list.append(item.strip("'"))
    return cleaned_ip_list
