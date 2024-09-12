<script type="module">
    document.addEventListener('DOMContentLoaded', () => {
        const searchInput = document.getElementById('searchInput');
        const huntTableBody = document.getElementById('huntTableBody');

        // Assuming huntEntries is already available as a JS array
        const hunts = JSON.parse(`{serializedHunts}`); // If huntEntries are serialized

        searchInput.addEventListener('input', () => {
            const query = searchInput.value.toLowerCase();
            
            hunts.forEach((hunt, index) => {
                const row = huntTableBody.querySelectorAll('tr')[index];

                // Extract necessary fields from huntEntries
                const id = hunt.id.toLowerCase();
                const title = hunt.data.title.toLowerCase();
                const hypothesis = hunt.data.hypothesis.toLowerCase();
                const description = hunt.data.description.toLowerCase();
                const analytics = hunt.data.analytics.map(a => a.name.toLowerCase()).join(" ");

                // Check if any of these fields contain the query
                const matches = id.includes(query) || 
                                title.includes(query) || 
                                hypothesis.includes(query) || 
                                description.includes(query) || 
                                analytics.includes(query);
                
                // Show or hide row based on match
                if (matches) {
                    row.style.display = '';
                } else {
                    row.style.display = 'none';
                }
            });
        });
    });
</script>
