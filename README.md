 <script
      src="https://unpkg.com/alpinejs@3.10.5/dist/cdn.min.js"
      defer
    ></script>
 
 <script>
      function getHuntEntries() {
        return {
          search: '',
          allHuntEntries: huntEntries,
          get filteredHuntEntries() {
            if (this.search === '') {
              return this.allHuntEntries;
            }
            return this.allHuntEntries.filter((entry) => {
              const searchTerm = this.search.replace(/ /g, '').toLowerCase();
              const title = entry.data.title.replace(/ /g, '').toLowerCase();
              const description = entry.data.description.replace(/ /g, '').toLowerCase();
              const hypothesis = entry.data.hypothesis.replace(/ /g, '').toLowerCase();

              return (
                title.includes(searchTerm) ||
                description.includes(searchTerm) ||
                hypothesis.includes(searchTerm)
              );
            });
          }
        };
      }

      // Sample hunt entries data
      const huntEntries = [
        {
          id: 'TH-0013',
          collection: 'hunts',
          data: {
            title: 'Sliver C2 Beacon Execution - MacOS',
            author: 'Paul Newton',
            creation_date: '2024/02/14',
            platform: 'EDR-Macos',
            attack_coverage: [{ technique: 'T1059' }, { technique: 'T1071' }],
            hypothesis:
              'Adversaries will attempt the execution of a payload to establish a C2. Sliver is a popular Open Source C2 framework.',
            description:
              'Modern C2 frameworks like Cobalt Strike and Sliver allow threat actors and Red Teamers to execute complex attacks...',
            hunter_notes: 'Review the application with the generic APP ID...',
          },
        },
        {
          id: 'TH-0014',
          collection: 'hunts',
          data: {
            title: 'Cobalt Strike Beacon Execution - Windows',
            author: 'Jane Doe',
            creation_date: '2024/03/22',
            platform: 'EDR-Windows',
            attack_coverage: [{ technique: 'T1080' }, { technique: 'T1095' }],
            hypothesis:
              'Cobalt Strike beacons allow attackers to maintain long-term access to a compromised system.',
            description:
              'Cobalt Strike is widely used in attacks, leveraging various exploitation techniques to remain persistent...',
            hunter_notes: 'Analyze network traffic for unusual beacon activity...',
          },
        },
        // More entries can be added here
      ];
    </script>




    <div
      class="container mx-auto"
      x-data="getHuntEntries()"
    >
      <!-- Search Input -->
      <input
        x-ref="searchInput"
        x-model="search"
        x-on:keydown.window.prevent.slash="$refs.searchInput.focus()"
        placeholder="Search for a hunt entry..."
        type="search"
        class="block w-full rounded bg-gray-200 p-4 mb-4"
      />

      <!-- Hunt Entries Grid -->
      <div class="grid sm:grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
        <!-- Loop through filtered hunt entries -->
        <template
          x-for="entry in filteredHuntEntries"
          :key="entry.id"
        >
          <div class="flex items-start shadow p-4 bg-white rounded-lg">
            <div class="ml-4">
              <p class="text-lg font-bold text-gray-900" x-text="entry.data.title"></p>
              <p class="text-sm text-gray-600 mb-2" x-text="entry.data.hypothesis"></p>
              <p class="text-sm text-gray-600">
                ATT&CK Techniques:
                <span
                  class="text-sm text-blue-600"
                  x-text="entry.data.attack_coverage.map(a => a.technique).join(', ')"
                ></span>
              </p>
              <p class="text-sm font-bold text-gray-600 mt-2" x-text="entry.data.platform"></p>
              <p class="text-sm text-gray-500" x-text="entry.data.creation_date"></p>
            </div>
          </div>
        </template>
      </div>
    </div>

