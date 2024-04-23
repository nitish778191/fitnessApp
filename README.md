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







<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> Report Details</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <style is:global>
     .dark-mode .collapsible, 
        .dark-mode .nested-collapsible {
            background-color: MidnightBlue;
            color: white;
        }

        .dark-mode .content, 
        .dark-mode .nested-content {
            background-color: #333;
        }
        .collapsible, .nested-collapsible {
            background-color: DodgerBlue;
            color: white;
            cursor: pointer;
            padding: 12px;
            border: none;
            text-align: left;
            outline: none;
            font-size: 14px;
            display: block;
            width: 100%;
            box-sizing: border-box;
            margin-top: 5px;
        }

        .active, .collapsible:hover, .nested-collapsible:hover {
            background-color: #1E90FF;
        }

        .content, .nested-content {
            padding: 0 18px;
            display: none;
            overflow: hidden;
            background-color: #f1f1f1;
            transition: max-height 0.2s ease-out;
            width: 100%;
            box-sizing: border-box;
        }

        table, .nested-table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 10px;
        }

        th, td, .nested-table th, .nested-table td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th, .nested-table th {
            background-color: #f3f3f3;
        }

        input[type="text"] {
            padding: 8px;
            width: 100%;
            margin-bottom: 12px;
        }

        #searchCount {
            background-color:DodgerBlue;
            border:1px solid #E2E8F0;
            padding:0.5rem 1rem;
            margin-top:1rem;
            color:white;
            height:50px;
            width:250px;
        }
    </style>
</head>
<body>
<div ></div>
    <div class="container mx-auto">
    <h2 id="searchCount"></h2>
        <h1 class="text-3xl font-bold mb-8 text-center">CrowdStrike Saved Search Detections</h1>
        <input type="text" id="searchInput" placeholder="Search by Name..." onkeyup="filterReports()">
        <div id="jsonContainer"></div>
    </div>

    <script is:inline>
    
    
    
const jsonData = [{}]



        const detailedKeys = ['schedule', 'last_execution', 'report_metadata', 'report_params', 'notifications', 'shared_with'];
        const container = document.getElementById('jsonContainer');
        let count=jsonData.length;

        jsonData.forEach((report, index) => {
            const reportButton = document.createElement('button');
            reportButton.textContent = report.name ? `${report.name} (ID: ${report.id})` : 'Unnamed Report';
            reportButton.className = 'collapsible';
            reportButton.dataset.name = report.name ? report.name.toLowerCase() : ''; // For searching by name

            const reportContent = document.createElement('div');
            reportContent.className = 'content';

            const reportTable = document.createElement('table');
            reportContent.appendChild(reportTable);

            Object.entries(report).forEach(([key, value]) => {
                const row = reportTable.insertRow();
                const keyCell = row.insertCell();
                keyCell.textContent = key;

                const valueCell = row.insertCell();
                if (detailedKeys.includes(key) && typeof value === 'object') {
                    const detailButton = document.createElement('button');
                    detailButton.textContent = `Toggle ${key}`;
                    detailButton.className = 'nested-collapsible';

                    const detailContent = document.createElement('div');
                    detailContent.className = 'nested-content';

                    const nestedTable = document.createElement('table');
                    nestedTable.className = 'nested-table';
                    Object.entries(value).forEach(([nestedKey, nestedValue]) => {
                        const nestedRow = nestedTable.insertRow();
                        const nestedKeyCell = nestedRow.insertCell();
                        nestedKeyCell.textContent = nestedKey;
                        const nestedValueCell = nestedRow.insertCell();
                        nestedValueCell.textContent = JSON.stringify(nestedValue, null, 2);
                    });

                    detailButton.onclick = function() {
                        this.classList.toggle("active");
                        detailContent.style.display = detailContent.style.display === 'block' ? 'none' : 'block';
                    };

                    detailContent.appendChild(nestedTable);
                    valueCell.appendChild(detailButton);
                    valueCell.appendChild(detailContent);
                } else {
                    valueCell.textContent = JSON.stringify(value, null, 2);
                }
            });

            container.appendChild(reportButton);
            container.appendChild(reportContent);

            reportButton.addEventListener('click', function() {
                this.classList.toggle("active");
                reportContent.style.display = reportContent.style.display === 'block' ? 'none' : 'block';
            });
        });

        
        updateSearchCount(count);
        function filterReports() {

            const input = document.getElementById('searchInput');
            const filter = input.value.trim().toLowerCase();
            const buttons = container.getElementsByClassName('collapsible');
            let count=0;

            for (let i = 0; i < buttons.length; i++) {
                const reportName = buttons[i].textContent.trim().toLowerCase();
                const reportContent = buttons[i].nextElementSibling;

                const searchWords = filter.split(/\s+/);

                const reportWords = reportName.split(/\s+/);

                const match = searchWords.some(searchWord => reportWords.some(reportWord => reportWord.includes(searchWord)));

                if (match) {
                    buttons[i].style.display = "block";
                    if (buttons[i].classList.contains('active')) {
                        reportContent.style.display = "block";
                    } else {
                        reportContent.style.display = "none";
                    }
                    count++;
                } else {
                    buttons[i].style.display = "none";
                    reportContent.style.display = "none";
                }
            }
            updateSearchCount(count);

}

        function updateSearchCount(count) {
    
            const searchCount = document.getElementById('searchCount');
            if (searchCount) {
                searchCount.textContent = `Number of search results: ${count}`;
            }
}


//function clearSearch() {
  //  const input = document.getElementById('searchInput');
    //input.value = ''; 
    //filterReports(); 
//}


function toggleDarkMode() {
            const body = document.body;
            body.classList.toggle('dark-mode');
        }


   // document.getElementById('searchInput').addEventListener('change',clearSearch);

    </script>
</body>
</html






_-_-&--_+-&--+_-+&+_



---
import { useToggle } from '@react-aria/toggle';

export let reportData: any;

const ReportDetails = () => {
  const [isExpanded, setIsExpanded] = useToggle(false);

  return (
    <div class="collapsible" aria-expanded={isExpanded}>
      <button onClick={setIsExpanded}>
        {reportData.name ? `${reportData.name} (ID: ${reportData.id})` : 'Unnamed Report'}
      </button>
      <div class="content" hidden={!isExpanded}>
        <table>
          <tbody>
            {Object.entries(reportData).map(([key, value]) => (
              <tr>
                <td>{key}</td>
                <td>
                  {typeof value === 'object' ? (
                    <button class="nested-collapsible">Toggle {key}</button>
                    <div class="nested-content" hidden>
                      <table class="nested-table">
                        <tbody>
                          {Object.entries(value).map(([nestedKey, nestedValue]) => (
                            <tr>
                              <td>{nestedKey}</td>
                              <td>{JSON.stringify(nestedValue)}</td>
                            </tr>
                          ))}
                        </tbody>
                      </table>
                    </div>
                  ) : (
                    JSON.stringify(value)
                  )}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default ReportDetails;




()(()reportdetails.astro()()()


---
component: ReportDetails
---
<template>
    <div class="container mx-auto">
        <h2 id="searchCount"></h2>
        <h1 class="text-3xl font-bold mb-8 text-center">CrowdStrike Saved Search Detections</h1>
        <input type="text" id="searchInput" placeholder="Search by Name..." onkeyup="filterReports()">
        <div id="jsonContainer"></div>
    </div>
</template>

<script>
    import { onMount } from 'astro';

    export default function ReportDetails() {
        onMount(() => {
            // Fetch JSON data
            fetch('/data/crowdstrike.json')
                .then(response => response.json())
                .then(jsonData => {
                    renderReports(jsonData); // Call function to render reports
                })
                .catch(error => console.error('Error fetching JSON:', error));
        });

        function renderReports(jsonData) {
            const detailedKeys = ['schedule', 'last_execution', 'report_metadata', 'report_params', 'notifications', 'shared_with'];
            const container = document.getElementById('jsonContainer');
            let count = jsonData.length;

            jsonData.forEach((report, index) => {
                // Rendering logic for each report
                // Example: Create HTML elements, insert data, etc.
            });

            updateSearchCount(count);
        }

        function updateSearchCount(count) {
            const searchCount = document.getElementById('searchCount');
            if (searchCount) {
                searchCount.textContent = `Number of search results: ${count}`;
            }
        }

        function filterReports() {
            // Filter and display reports based on user input
            // Example: Get search input, filter reports, update display
        }
    }
</script>

<style>
    /* Your CSS styles here */
</style>








())()()))))))(()

layout.astro
++-+-+-
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>{title}</title>
    <!-- Include any global CSS or meta tags here -->
</head>
<body>
    <header>
        <!-- Include any header content here -->
    </header>

    <main>
        <!-- This is where the content of each page will be inserted -->
        <slot />
    </main>

    <footer>
        <!-- Include any footer content here -->
    </footer>

    <!-- Include any global scripts here -->
</body>
</html>



search    

---
export let searchResults: number;

const SearchResultBox = () => {
  return (
    <div class="search-result-box bg-blue-500 text-white p-4">
      <p class="font-bold">Number of Search Results: {searchResults}</p>
    </div>
  );
};

export default SearchResultBox;
index
---
import ReportDetails from './ReportDetails.astro';
import SearchResultBox from './SearchResultBox.astro';

const jsonData = [
  // Your JSON data array here
];

const detailedKeys = ['schedule', 'last_execution', 'report_metadata', 'report_params', 'notifications', 'shared_with'];

const Index = () => {
  const filterReports = (event: any) => {
    const filter = event.target.value.trim().toLowerCase();
    const buttons = document.getElementsByClassName('collapsible');
    let count = 0;

    for (let i = 0; i < buttons.length; i++) {
      const reportName = buttons[i].textContent.trim().toLowerCase();
      const reportContent = buttons[i].nextElementSibling;

      const reportWords = reportName.split(/\s+/);
      const match = reportWords.some(word => word.includes(filter));

      if (match) {
        buttons[i].style.display = "block";
        reportContent.style.display = buttons[i].classList.contains('active') ? "block" : "none";
        count++;
      } else {
        buttons[i].style.display = "none";
        reportContent.style.display = "none";
      }
    }

    document.getElementById('searchResultBox').searchResults = count;
  }

  return (
    <div class="container mx-auto">
      <h1 class="text-3xl font-bold mb-8 text-center">Enhanced ID-Based Report Details</h1>
      <input type="text" id="searchInput" placeholder="Search by Name..." onkeyup={filterReports} class="p-2 mb-4" />
      <div id="jsonContainer">
        {jsonData.map(report => (
          <ReportDetails report={report} detailedKeys={detailedKeys} />
        ))}
      </div>
      <SearchResultBox id="searchResultBox" />
    </div>
  );
}

export default Index;

global.css

---
const styles = `
  /* Add your global styles here */
  body {
    font-family: 'Roboto', sans-serif;
    margin: 0;
    padding: 0;
  }

  /* Add more global styles as needed */
`;

export const stylesHtml = `<style>${styles}</style>`;

