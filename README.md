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


;;';'

;';'
;';'

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Search Table Example</title>
  <script src="https://unpkg.com/alpinejs@3.10.5/dist/cdn.min.js" defer></script>
  <style>
    .border-lseg-lightgrey {
      border-color: #dcdcdc;
    }
    .text-lseg-darkgrey {
      color: #4a4a4a;
    }
    .hover\:text-lseg-blue:hover {
      color: #1f78b4;
    }
  </style>
</head>
<body class="bg-gray-100 text-gray-900 px-4 py-8">
  <div x-data="getHuntEntries()">
    <!-- Search Input -->
    <input
      x-model="search"
      placeholder="Search for a hunt entry..."
      type="search"
      class="block w-full rounded bg-gray-200 p-4 mb-4"
    />

    <!-- Hunt Entries Table -->
    <table class="w-full table-auto border-collapse text-md">
      <thead class="text-lseg-meddarkgrey">
        <tr>
          <th scope="col" class="py-2 px-4 text-center">ID</th>
          <th scope="col" class="hidden lg:table-cell py-2 px-4 text-center">Ticket</th>
          <th scope="col" class="py-2 px-4 text-left">Title</th>
          <th scope="col" class="py-2 px-4 text-left">ATT&CK Techniques</th>
          <th scope="col" class="hidden lg:table-cell py-2 px-4 text-center">Platform</th>
          <th scope="col" class="py-2 px-4 text-center">Creation Date</th>
        </tr>
      </thead>
      <tbody id="huntTableBody">
        <!-- Loop through hunt entries where ID matches the search results -->
        <template x-for="hunt in filteredHuntEntries" :key="hunt.id">
          <tr class="text-lseg-darkgrey text-sm">
            <td class="border-t border-b border-lseg-lightgrey text-center whitespace-nowrap">
              <a class="py-4 px-4 hover:text-lseg-blue hover:underline" :href="hunt.id.toLowerCase()">
                <span x-text="hunt.id"></span>
              </a>
            </td>
            <td class="hidden lg:table-cell border-t border-b border-lseg-lightgrey py-4 px-4 text-center">
              <span x-text="hunt.data.hunt_ticket"></span>
            </td>
            <td class="border-t border-b border-lseg-lightgrey text-left">
              <a class="block py-4 px-4 hover:text-lseg-blue hover:underline" :href="hunt.id.toLowerCase()">
                <span x-text="hunt.data.title"></span>
              </a>
            </td>
            <td class="border-t border-b border-lseg-lightgrey py-4 px-4 text-left">
              <div class="flex flex-wrap gap-4">
                <template x-for="item in hunt.data.attack_coverage">
                  <div>
                    <a
                      class="hover:underline hover:text-lseg-blue"
                      :title="item.technique"
                      :href="'/technique/' + item.technique"
                      x-text="item.technique"
                    ></a>
                    <template x-for="subtech in item.subtechniques">
                      <a
                        class="hover:underline hover:text-lseg-blue"
                        :title="subtech"
                        :href="'/technique/' + subtech"
                        x-text="subtech"
                      ></a>
                    </template>
                  </div>
                </template>
              </div>
            </td>
            <td class="hidden lg:table-cell border-t border-b border-lseg-lightgrey py-4 px-4 text-center">
              <span x-text="hunt.data.platform"></span>
            </td>
            <td class="border-t border-b border-lseg-lightgrey py-4 px-4 text-center">
              <span x-text="hunt.data.creation_date"></span>
            </td>
          </tr>
        </template>
      </tbody>
    </table>
  </div>

  <script>
    function getHuntEntries() {
      return {
        search: '',
        allHuntEntries: [
          {
            id: 'TH-0013',
            data: {
              hunt_ticket: 'TK-0013',
              title: 'Silver C2 Beacon Execution - MacOS',
              attack_coverage: [
                { technique: 'T1059', subtechniques: ['T1059.001', 'T1059.003'] },
                { technique: 'T1071' }
              ],
              platform: 'MacOS',
              creation_date: '2024/02/14',
              hypothesis: 'Adversaries will attempt the execution of a payload to establish a C2.'
            }
          },
          {
            id: 'TH-0027',
            data: {
              hunt_ticket: 'TK-0027',
              title: 'Credential Dumping - Windows',
              attack_coverage: [{ technique: 'T1003' }],
              platform: 'Windows',
              creation_date: '2024/03/02',
              hypothesis: 'Threat actors may attempt credential dumping to gain unauthorized access.'
            }
          }
        ],
        get filteredHuntEntries() {
          // Perform search and return only matching hunt entries by ID
          if (this.search === '') {
            return this.allHuntEntries;
          }

          const matchedIds = this.allHuntEntries
            .filter(hunt => {
              const searchTerm = this.search.toLowerCase();
              return (
                hunt.id.toLowerCase().includes(searchTerm) ||
                hunt.data.title.toLowerCase().includes(searchTerm) ||
                hunt.data.hypothesis.toLowerCase().includes(searchTerm)
              );
            })
            .map(hunt => hunt.id);

          // Return only the entries whose IDs match
          return this.allHuntEntries.filter(hunt => matchedIds.includes(hunt.id));
        }
      };
    }
  </script>
</body>
</html>

