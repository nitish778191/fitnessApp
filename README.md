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
