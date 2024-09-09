---
import { huntEntries } from "../../huntcontent";
import { techniques } from "../../mitre";
import Layout from "../../layouts/Layout.astro";

const baseURL = import.meta.env.BASE_URL;
---

<Layout title="All Hunts">
    <main class="max-w-screen-xl mx-10 mt-10 2x1:mx-auto" x-data="{ searchQuery: '' }">
       
        <h1 class="my-5 text-2xl font-lseg">All Hunts</h1>
        
        <!-- Search Box -->
        <input
            placeholder="Search using Hunt ID or Title..."
            type="search"
            class="block w-full rounded bg-gray-200 p-4 mb-4"
            x-model="searchQuery"
        />

        <div class="max-w-screen-xl mx-10 mt-10 2xl:mx-auto my-5 text-sm flex flex-row-reverse gap-8 font-sans">
            <a class="rounded-md py-1 px-3 bg-gray-700 text-white" href="hunts.json" download>Download all hunt data (JSON)</a>
        </div>

        <!-- Table for Hunt Entries -->
        <table class="w-full table-auto border-collapse text-md">
            <thead class="text-lseg-meddarkgrey">
                <th scope="col" class="py-2 px-4 text-center">ID</th>
                <th scope="col" class="hidden lg:table-cell py-2 px-4 text-center">Ticket</th>
                <th scope="col" class="py-2 px-4 text-left">Title</th>
                <th scope="col" class="py-2 px-4 text-left">ATT&CK Techniques</th>
                <th scope="col" class="hidden lg:table-cell py-2 px-4 text-center">Platform</th>
                <th scope="col" class="py-2 px-4 text-center">Creation Date</th>
            </thead>
            <tbody>
                <!-- Filter and Display Hunt Entries based on search query -->
                <template x-for="hunt in filteredHunts()" :key="hunt.id">
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
                                    <a :title="getTechniqueName(item.technique)" class="hover:underline hover:text-lseg-blue" :href="`${baseURL}/technique/${item.technique}`">
                                        <span x-text="item.technique"></span>
                                    </a>
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
    </main>

    <script>
        function filteredHunts() {
            return {
                // Return hunts based on search query (case insensitive)
                filteredHunts() {
                    if (this.searchQuery.trim() === '') {
                        return huntEntries;
                    }
                    return huntEntries.filter(hunt => {
                        return hunt.id.toLowerCase().includes(this.searchQuery.toLowerCase()) ||
                               hunt.data.title.toLowerCase().includes(this.searchQuery.toLowerCase());
                    });
                },
                getTechniqueName(techniqueId) {
                    const technique = techniques.find(t => t.id === techniqueId);
                    return technique ? technique.name : '';
                }
            }
        }
    </script>
</Layout>
