<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>JSON Console</title>
  </head>
  <body>
    <p>HI</p>
    <script>
      // Function to fetch JSON data and console.log it
      function fetchAndLogJSON(url) {
        fetch(url)
          .then((response) => {
            if (!response.ok) {
              throw new Error("Network response was not ok");
            }
            return response.json();
          })
          .then((data) => {
            console.log("JSON Data:", data);
          })
          .catch((error) => {
            console.error(
              "There was a problem with the fetch operation:",
              error
            );
          });
      }

      // Call the function with the URL of your JSON file
      fetchAndLogJSON("package.json");
    </script>
  </body>
</html>
