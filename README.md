import json

def correct_json_format(json_file_path):
    try:
        # Read JSON file
        with open(json_file_path, 'r') as file:
            data = file.read()
        
        # Remove spaces in timestamps
        data = data.replace(': ', ':')
        
        # Add missing commas between elements in the JSON array
        data = data.replace('} {', '}, {')
        
        # Enclose keys and values in double quotes
        data = data.replace("'", '"')
        
        # Write corrected data back to file
        with open(json_file_path, 'w') as file:
            file.write(data)
        
        print("JSON formatting corrected successfully.")
    except FileNotFoundError:
        print("File not found.")
    except Exception as e:
        print(f"An error occurred: {e}")

# Example usage:
json_file_path = 'package.json'  # Change this to your JSON file path
correct_json_format(json_file_path)








import json

# Read JSON data from file
with open('example.json', 'r') as file:
    data = json.load(file)

# Correct the format
corrected_data = []
for item in data:
    # Convert 'shared_with' list to correct format
    shared_with = item.get('shared_with', [])
    corrected_shared_with = []
    for val in shared_with:
        if isinstance(val, str) and val.strip():
            corrected_shared_with.append(val.strip())
        elif isinstance(val, int):
            corrected_shared_with.append(val)
    item['shared_with'] = corrected_shared_with
    corrected_data.append(item)

# Write corrected data to a new JSON file
with open('corrected_example.json', 'w') as outfile:
    json.dump(corrected_data, outfile, indent=4)

print("Data has been corrected and saved to corrected_example.json")




<!DOCTYPE html>
<html lang="en" class="light-mode">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Horizontal Report Details Display</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <style>
        /* Your existing styles */
        /* Add additional styles for dark mode */
        .dark-mode .collapsible, 
        .dark-mode .nested-collapsible {
            background-color: MidnightBlue;
            color: white;
        }

        .dark-mode .content, 
        .dark-mode .nested-content {
            background-color: #333;
        }
    </style>
</head>
<body>
    <!-- Your existing HTML content -->

    <!-- JavaScript code for toggling dark mode -->
    <script>
        function toggleDarkMode() {
            const body = document.body;
            body.classList.toggle('dark-mode');
        }
    </script>
</body>
</html>
