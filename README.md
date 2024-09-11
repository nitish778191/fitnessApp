---
import { huntEntries } from "../../huntcontent";
import { techniques } from "../../mitre";
import Layout from "../../layouts/Layout.astro";

const baseURL = import.meta.env.BASE_URL;
const serializedHunts = JSON.stringify(huntEntries); // Serialize huntEntries to pass to the client
---

<Layout title="All Hunts">
    <main class="max-w-screen-xl mx-10 mt-10 2xl:mx-auto" x-data="{
        searchQuery: '',
        hunts: JSON.parse(`{{ serializedHunts }}`),
        filteredHunts() {
            if (!this.searchQuery) {
                return this.hunts; // Return all hunts if no search query
            }
            const query = this.searchQuery.toLowerCase();
            return this.hunts.filter(hunt =>
                hunt.id.toLowerCase().includes(query) || 
                hunt.data.title.toLowerCase().includes(query)
            );
        }
    }">
        <h1 class="my-5 text-2xl font-lseg">All Hunts</h1>
        
        <!-- Search Input -->
        <input
            placeholder="Search using Hunt ID or Title..."
            type="search"
            x-model="searchQuery"
            class="block w-full rounded bg-gray-200 p-4 mb-4"
        />

        <div class="max-w-screen-xl mx-10 mt-10 2xl:mx-auto my-5 text-sm flex flex-row-reverse gap-8 font-sans">
            <a class="rounded-md py-1 px-3 bg-gray-700 text-white" href="hunts.json" download>Download all hunt data (JSON)</a>
        </div>
        
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
                <template x-for="hunt in filteredHunts()" :key="hunt.id">
                    <tr class="text-lseg-darkgrey text-sm">
                        <td class="border-t border-b border-lseg-lightgrey text-center whitespace-nowrap">
                            <a class="py-4 px-4 hover:text-lseg-blue hover:underline" 
                               :href="hunt.id.toLowerCase()">
                               {{ hunt.id }}
                            </a>
                        </td>
                        <td class="hidden lg:table-cell border-t border-b border-lseg-lightgrey py-4 px-4 text-center">{{ hunt.data.hunt_ticket }}</td>
                        <td class="border-t border-b border-lseg-lightgrey text-left">
                            <a class="block py-4 px-4 hover:text-lseg-blue hover:underline" :href="hunt.id.toLowerCase()">
                                {{ hunt.data.title }}
                            </a>
                        </td>
                        <td class="border-t border-b border-lseg-lightgrey py-4 px-4 text-left">
                            <div class="flex flex-wrap gap-4">
                                <template x-for="item in hunt.data.attack_coverage" :key="item.technique">
                                    <div>
                                        <a :title="techniques.find(t => t.id === item.technique)?.name" 
                                           class="hover:underline hover:text-lseg-blue" 
                                           :href="baseURL + '/technique/' + item.technique">
                                            {{ item.technique }}
                                        </a>
                                        <template x-for="subtech in item.subtechniques || []" :key="subtech">
                                            <a class="hover:underline hover:text-lseg-blue" 
                                               :title="techniques.find(t => t.id === subtech)?.name" 
                                               :href="baseURL + '/technique/' + subtech">
                                                {{ subtech }}
                                            </a>
                                        </template>
                                    </div>
                                </template>
                            </div>
                        </td>
                        <td class="hidden lg:table-cell border-t border-b border-lseg-lightgrey py-4 px-4 text-center">{{ hunt.data.platform }}</td>
                        <td class="border-t border-b border-lseg-lightgrey py-4 px-4 text-center">{{ hunt.data.creation_date }}</td>
                    </tr>
                </template>
            </tbody>
        </table>
    </main>
</Layout>
