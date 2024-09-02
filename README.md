  <script>
    function searchComponent() {
      return {
        searchQuery: '', // Search input query
        hunts: ${JSON.stringify(huntEntries)}, // Data fetched from server
        get filteredHunts() {
          // Filter hunt entries based on search query for ID or Title
          if (this.searchQuery.trim() === '') {
            return this.hunts;
          }

          const query = this.searchQuery.toLowerCase();
          return this.hunts.filter(hunt =>
            hunt.id.toLowerCase().includes(query) ||  // Search by ID
            hunt.data.title.toLowerCase().includes(query)  // Search by Title
          );
        },
        getTechniqueName(id) {
          // Helper method to get the technique name
          const technique = techniques.find(t => t.id === id);
          return technique ? technique.name : id;
        }
      };
    }
  </script>
