 <main class="max-w-screen-xl mx-10 mt-10 2x1:mx-auto" x-data="{
      searchQuery: '', 
      hunts: ${JSON.stringify(huntEntries)}, 
      techniques: ${JSON.stringify(techniques)},
      get filteredHunts() {
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
        const technique = this.techniques.find(t => t.id === id);
        return technique ? technique.name : id;
      }
  }">
