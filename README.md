<script>
  function searchComponent() {
    return {
      searchQuery: '',
      huntEntries: JSON.parse(document.getElementById('huntEntriesData').textContent),
      get filteredItems() {
        const query = this.searchQuery.toLowerCase();
        return this.huntEntries.filter(entry => {
          const id = entry.id.toLowerCase();
          const title = entry.data.title.toLowerCase();
          const hypothesis = entry.data.hypothesis.toLowerCase();
          const description = entry.data.description.toLowerCase();
          return id.includes(query) || title.includes(query) || hypothesis.includes(query) || description.includes(query);
        });
      }
    };
  }
</script>
