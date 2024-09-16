<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Hunt Entries Search</title>
    <link
      href="https://unpkg.com/tailwindcss@^1.0/dist/tailwind.min.css"
      rel="stylesheet"
    />
    <script src="https://unpkg.com/alpinejs@3.10.5/dist/cdn.min.js" defer></script>

    <!-- JavaScript Import for Hunt Entries -->
    <script type="module">
      import { huntEntries } from '../../huntcontent';

      function getHuntEntries() {
        return {
          search: '',
          allHuntEntries: huntEntries, // Imported data
          get filteredHuntEntries() {
            if (this.search === '') {
              return this.allHuntEntries;
            }
            return this.allHuntEntries.filter((entry) => {
              return entry.data.title
                .toLowerCase()
                .includes(this.search.toLowerCase());
            });
          },
        };
      }

      // Expose this function globally so Alpine.js can access it
      window.getHuntEntries = getHuntEntries;
    </script>
  </head>
  <body class="bg-gray-100 text-gray-900 p-6">
    <div class="container mx-auto" x-data="getHuntEntries()">
      <!-- Search Input -->
      <input
        x-ref="searchInput"
        x-model="search"
        x-on:keydown.window.prevent.slash="$refs.searchInput.focus()"
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
        <tbody>
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
                  <template x-for="technique in hunt.data.attack_coverage">
                    <a
                      class="hover:underline hover:text-lseg-blue"
                      :href="'/technique/' + technique.technique"
                      x-text="technique.technique"
                    ></a>
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
  </body>
</html>
