    <Layout title="All Hunts">
      <main className="max-w-screen-xl mx-10 mt-10 2xl:mx-auto">
        <h1 className="my-5 text-2xl font-bold">All Hunts</h1>
        
        <div x-data="getHuntEntries()">
          {/* Search Input */}
          <input
            x-ref="searchInput"
            x-model="search"
            x-on:keydown.window.prevent.slash="$refs.searchInput.focus()"
            placeholder="Search for a hunt entry..."
            type="search"
            className="block w-full rounded bg-gray-200 p-4 mb-4"
          />

          {/* Hunt Entries Table */}
          <table className="w-full table-auto border-collapse text-md">
            <thead className="text-lseg-meddarkgrey">
              <tr>
                <th scope="col" className="py-2 px-4 text-center">ID</th>
                <th scope="col" className="hidden lg:table-cell py-2 px-4 text-center">Ticket</th>
                <th scope="col" className="py-2 px-4 text-left">Title</th>
                <th scope="col" className="py-2 px-4 text-left">ATT&CK Techniques</th>
                <th scope="col" className="hidden lg:table-cell py-2 px-4 text-center">Platform</th>
                <th scope="col" className="py-2 px-4 text-center">Creation Date</th>
              </tr>
            </thead>
            <tbody>
              <template x-for="hunt in filteredHuntEntries" :key="hunt.id">
                <tr className="text-lseg-darkgrey text-sm">
                  <td className="border-t border-b border-lseg-lightgrey text-center whitespace-nowrap">
                    <a
                      className="py-4 px-4 hover:text-lseg-blue hover:underline"
                      :href="hunt.id.toLowerCase()"
                      x-text="hunt.id"
                    ></a>
                  </td>
                  <td className="hidden lg:table-cell border-t border-b border-lseg-lightgrey py-4 px-4 text-center">
                    <span x-text="hunt.data.hunt_ticket"></span>
                  </td>
                  <td className="border-t border-b border-lseg-lightgrey text-left">
                    <a
                      className="block py-4 px-4 hover:text-lseg-blue hover:underline"
                      :href="hunt.id.toLowerCase()"
                      x-text="hunt.data.title"
                    ></a>
                  </td>
                  <td className="border-t border-b border-lseg-lightgrey py-4 px-4 text-left">
                    <div className="flex flex-wrap gap-4">
                      <template x-for="item in hunt.data.attack_coverage">
                        <a
                          :href="'/technique/' + item.technique"
                          className="hover:underline hover:text-lseg-blue"
                          x-text="item.technique"
                        ></a>
                      </template>
                    </div>
                  </td>
                  <td className="hidden lg:table-cell border-t border-b border-lseg-lightgrey py-4 px-4 text-center">
                    <span x-text="hunt.data.platform"></span>
                  </td>
                  <td className="border-t border-b border-lseg-lightgrey py-4 px-4 text-center">
                    <span x-text="hunt.data.creation_date"></span>
                  </td>
                </tr>
              </template>
            </tbody>
          </table>
        </div>
      </main>

      <script>
        function getHuntEntries() {
          return {
            search: "",
            allHuntEntries: huntEntries,
            get filteredHuntEntries() {
              if (this.search === "") {
                return this.allHuntEntries;
              }
              return this.allHuntEntries.filter((hunt) => {
                return (
                  hunt.id.toLowerCase().includes(this.search.toLowerCase()) ||
                  hunt.data.title.toLowerCase().includes(this.search.toLowerCase()) ||
                  hunt.data.hypothesis.toLowerCase().includes(this.search.toLowerCase()) ||
                  hunt.data.description.toLowerCase().includes(this.search.toLowerCase())
                );
              });
            },
          };
        }
      </script>
    </Layout>
